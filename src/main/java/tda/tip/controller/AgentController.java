package tda.tip.controller;

import java.util.List;
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

import tda.tip.Repository.AgentRepository;
import tda.tip.entity.Agent;
import tda.tip.entity.EmployeeRole;
import tda.tip.Repository.EmployeeRoleRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping(value = "/agent")
    public List<Agent> getEmployees() {
        // employeeRepository.save(dang);
        List<Agent> employees = agentRepository.findAll();
        return employees;
    }

    @PostMapping(value = "/selagent")
    public ResponseEntity<Agent> getAgentByUsername(@RequestBody HashMap<String, String> requestBody) {
        String username = requestBody.get("username");
        Agent agent = agentRepository.findByUsername(username);
        if (agent != null) {
            Agent a = new Agent(agent.getId(), agent.getUsername());
            return new ResponseEntity<Agent>(a, HttpStatus.OK);
        } else {
            return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getnext")
    public HashMap<String, Object> getNextAgent() {
        Query query = entityManager.createNativeQuery("SELECT MAX(id) FROM agent");
        Integer maxId = (Integer) query.getSingleResult();
        Integer nextId = maxId != null ? maxId + 1 : 1;
        HashMap<String, Object> result = new HashMap<>();
        result.put("nextId", nextId);
        return result;
    }

    @GetMapping(value = "/agent/{id}")
    public ResponseEntity<Agent> getEmployee(@PathVariable("id") int id) {
        Optional<Agent> opt = agentRepository.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<Agent>(opt.get(), HttpStatus.OK);
        else
            return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/agent/{id}")
    public ResponseEntity<Agent> putEmployee(@PathVariable("id") int id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("salary") int salary) {
        Optional<Agent> opt = agentRepository.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);

        Agent agent = opt.get();
        agent.setFirstname(firstname);
        agent.setLastname(lastname);
        agentRepository.save(agent);
        return new ResponseEntity<Agent>(agent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/agent/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
        try {
            agentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("deleted done", HttpStatus.OK);
    }

    @PostMapping(value = "/agent")
    public ResponseEntity<Agent> postEmployees(@RequestBody Agent agent) {
        agentRepository.save(agent);
        return new ResponseEntity<Agent>(agent, HttpStatus.OK);
    }
}
