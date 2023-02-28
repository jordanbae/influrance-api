package tda.tip.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tda.tip.Repository.AgentCustomerOrderRepository;
import tda.tip.entity.AgentCustomerOrder;
import tda.tip.entity.EmailDetails;
import tda.tip.service.EmailService;

@Controller
public class ReportController {
	@Autowired
	private AgentCustomerOrderRepository repository;
	@Autowired
	private EmailService emailService;

	@GetMapping("/pdf")
	public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {
		Optional<AgentCustomerOrder> opt = repository.findById(1);
		ObjectMapper mapObject = new ObjectMapper();
		Map<String, Object> mapObj = mapObject.convertValue(opt.get(), Map.class);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(
				mapObj), false);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Influrance Co., Ltd.");

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/ainvoice.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "invoice.pdf");

		byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

		System.err.println("DATAAAAA" + data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		// Send email with attachment
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient("jordanlaphon@gmail.com");
		emailDetails.setSubject("Invoice");
		emailDetails.setMsgBody("Please find attached the invoice.");

		String status = emailService.sendMailWithAttachment(emailDetails, data);

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
