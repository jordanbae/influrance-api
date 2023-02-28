package tda.tip.controller;

import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.http.ResponseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tda.tip.service.EmailService;
import tda.tip.Repository.AgentRepository;
import tda.tip.entity.AgentCustomerOrder;
import tda.tip.entity.EmailDetails;
import tda.tip.entity.Agent;
import tda.tip.entity.AgentCustomer;
import tda.tip.Repository.AgentCustomerOrderRepository;
import tda.tip.entity.EmployeeRole;
import tda.tip.Repository.EmployeeRoleRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
public class AgentCustomerOrderController {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AgentCustomerOrderRepository agentCustomerOrderRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/getnextorder")
    public HashMap<String, Object> getNextOrder() {
        Query query = entityManager.createNativeQuery("SELECT MAX(id) FROM agent_customer_order");
        Integer maxId = (Integer) query.getSingleResult();
        Integer nextId = maxId != null ? maxId + 1 : 1;
        HashMap<String, Object> result = new HashMap<>();
        result.put("nextId", nextId);
        return result;
    }
    // @PostMapping(value = "/buy")
    // public ResponseEntity<AgentCustomerOrder> postOrder(@RequestBody
    // AgentCustomerOrder order) {
    // agentCustomerOrderRepository.save(order);
    // return new ResponseEntity<AgentCustomerOrder>(order, HttpStatus.OK);
    // }

    @PostMapping(value = "/buy")
    public ResponseEntity<AgentCustomerOrder> postOrder(@RequestBody AgentCustomerOrder order)
            throws JRException, IOException {
        agentCustomerOrderRepository.save(order);
        byte[] pdfInvoice = downloadInvoice(order);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("jordanlaphon@gmail.com");
        // emailDetails.setRecipient(order.getEmail());
        emailDetails.setMsgBody(
                "Thank you for your purchase with Influrance! \n Please find attached the PDF invoice for your recent purchase.");
        emailDetails.setSubject("Invoice for Purchase #" + order.getOrder_id());
        emailDetails.setAttachment("invoice.pdf");
        emailService.sendMailWithAttachment(emailDetails, pdfInvoice);
        return new ResponseEntity<AgentCustomerOrder>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/updatebuy/{id}")
    public ResponseEntity<AgentCustomerOrder> updateOrderDraft(@PathVariable("id") Integer id,
            @RequestBody AgentCustomerOrder orderDraft) throws JRException, IOException {
        Optional<AgentCustomerOrder> orderDraftOptional = agentCustomerOrderRepository.findById(id);
        if (!orderDraftOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AgentCustomerOrder existingOrderDraft = orderDraftOptional.get();
        existingOrderDraft.setOrder_id(orderDraft.getOrder_id());
        existingOrderDraft.setProduct_id(orderDraft.getProduct_id());
        existingOrderDraft.setCustomer_id(orderDraft.getCustomer_id());
        existingOrderDraft.setAgentId(orderDraft.getAgentId());
        existingOrderDraft.setProduct_name(orderDraft.getProduct_name());
        existingOrderDraft.setProduct_price(orderDraft.getProduct_price());
        existingOrderDraft.setCoverage_allowance(orderDraft.getCoverage_allowance());
        existingOrderDraft.setCoverage_claimed(orderDraft.getCoverage_claimed());
        existingOrderDraft.setCoverage_left(orderDraft.getCoverage_left());
        existingOrderDraft.setPrefix(orderDraft.getPrefix());
        existingOrderDraft.setFirstname(orderDraft.getFirstname());
        existingOrderDraft.setLastname(orderDraft.getLastname());
        existingOrderDraft.setCitizen_id(orderDraft.getCitizen_id());
        existingOrderDraft.setEmail(orderDraft.getEmail());
        existingOrderDraft.setAddress(orderDraft.getAddress());
        existingOrderDraft.setDistrict(orderDraft.getDistrict());
        existingOrderDraft.setSub_district(orderDraft.getSub_district());
        existingOrderDraft.setPostal_code(orderDraft.getPostal_code());
        existingOrderDraft.setProvince(orderDraft.getProvince());
        existingOrderDraft.setBirthdate(orderDraft.getBirthdate());
        existingOrderDraft.setStart_date(orderDraft.getStart_date());
        existingOrderDraft.setEnd_date(orderDraft.getEnd_date());
        existingOrderDraft.setBeneficiary_1_prefix(orderDraft.getBeneficiary_1_prefix());
        existingOrderDraft.setBeneficiary_1_firstname(orderDraft.getBeneficiary_1_firstname());
        existingOrderDraft.setBeneficiary_1_lastname(orderDraft.getBeneficiary_1_lastname());
        existingOrderDraft.setBeneficiary_1_relationship(orderDraft.getBeneficiary_1_relationship());
        existingOrderDraft.setBeneficiary_1_phone_number(orderDraft.getBeneficiary_1_phone_number());
        existingOrderDraft.setBeneficiary_2_prefix(orderDraft.getBeneficiary_2_prefix());
        existingOrderDraft.setBeneficiary_2_firstname(orderDraft.getBeneficiary_2_firstname());
        existingOrderDraft.setBeneficiary_2_lastname(orderDraft.getBeneficiary_2_lastname());
        existingOrderDraft.setBeneficiary_2_relationship(orderDraft.getBeneficiary_2_relationship());
        existingOrderDraft.setBeneficiary_2_phone_number(orderDraft.getBeneficiary_2_phone_number());
        existingOrderDraft.setBeneficiary_3_prefix(orderDraft.getBeneficiary_3_prefix());
        existingOrderDraft.setBeneficiary_3_firstname(orderDraft.getBeneficiary_3_firstname());
        existingOrderDraft.setBeneficiary_3_lastname(orderDraft.getBeneficiary_3_lastname());
        existingOrderDraft.setBeneficiary_3_relationship(orderDraft.getBeneficiary_3_relationship());
        existingOrderDraft.setBeneficiary_3_phone_number(orderDraft.getBeneficiary_3_phone_number());
        existingOrderDraft.setPurchase_date(orderDraft.getPurchase_date());
        existingOrderDraft.setorderStatus(orderDraft.getorderStatus());

        AgentCustomerOrder updatedOrderDraft = agentCustomerOrderRepository.save(existingOrderDraft);
        byte[] pdfInvoice = downloadInvoice(updatedOrderDraft);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("jordanlaphon@gmail.com");
        // emailDetails.setRecipient(order.getEmail());
        emailDetails.setMsgBody(
                "Thank you for your purchase with Influrance! \n Please find attached the PDF invoice for your recent purchase.");
        emailDetails.setSubject("Invoice for Purchase #" + existingOrderDraft.getOrder_id());
        emailDetails.setAttachment("invoice.pdf");
        emailService.sendMailWithAttachment(emailDetails, pdfInvoice);
        return new ResponseEntity<>(updatedOrderDraft, HttpStatus.OK);
    }

    private byte[] downloadInvoice(AgentCustomerOrder order) throws JRException, IOException {
        ObjectMapper mapObject = new ObjectMapper();
        Map<String, Object> mapObj = mapObject.convertValue(order, Map.class);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
                mapObj), false);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Influrance Co., Ltd.");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/invoice.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "invoice.pdf");
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        return data;
    }

    @PostMapping(value = "/draft")
    public ResponseEntity<AgentCustomerOrder> postOrderDraft(@RequestBody AgentCustomerOrder draft)
            throws JRException, IOException {
        agentCustomerOrderRepository.save(draft);
        return new ResponseEntity<AgentCustomerOrder>(draft, HttpStatus.OK);
    }

    @PostMapping(value = "/getdraft")
    public ResponseEntity<List<AgentCustomerOrder>> getOrderDraft(@RequestBody HashMap<String, Object> requestBody) {
        Long aid = Long.parseLong(requestBody.get("aid").toString());
        List<AgentCustomerOrder> drafts = agentCustomerOrderRepository.findByAgentIdAndOrderStatus(aid, "draft");
        if (!drafts.isEmpty()) {
            return new ResponseEntity<List<AgentCustomerOrder>>(drafts, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<AgentCustomerOrder>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getdraft/{id}")
    public ResponseEntity<AgentCustomerOrder> getDraftById(@PathVariable Integer id) {
        Optional<AgentCustomerOrder> draft = agentCustomerOrderRepository.findById(id);
        if (draft.isPresent() && draft.get().getorderStatus().equals("draft")) {
            return new ResponseEntity<>(draft.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
