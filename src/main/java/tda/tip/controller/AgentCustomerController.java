package tda.tip.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tda.tip.Repository.AgentCustomerRepository;
import tda.tip.entity.AgentCustomer;
import tda.tip.entity.EmployeeRole;
import tda.tip.Repository.EmployeeRoleRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
public class AgentCustomerController {
    @Autowired
    private AgentCustomerRepository agentCustomerRepository;

    @PostMapping(value = "/agent-customer")
    public ResponseEntity<AgentCustomer> postEmployees(@RequestBody AgentCustomer agentCustomer) {
        agentCustomerRepository.save(agentCustomer);
        return new ResponseEntity<AgentCustomer>(agentCustomer, HttpStatus.OK);
    }

    // FIX
    // THISSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    // TO GET ALL RELATION FOR AGENT DASHBOARD
    @PostMapping(value = "/relation")
    public ResponseEntity<List<AgentCustomer>> getAgentByUsername(@RequestBody HashMap<String, Long> requestBody) {
        Long aid = requestBody.get("aid");
        List<AgentCustomer> agents = agentCustomerRepository.findByAgentId(aid);
        if (!agents.isEmpty()) {
            return new ResponseEntity<List<AgentCustomer>>(agents, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<AgentCustomer>>(HttpStatus.NOT_FOUND);
        }
    }
}
