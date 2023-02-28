package tda.tip.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import tda.tip.Repository.AgentRepository;
import tda.tip.entity.Agent;
import tda.tip.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AgentRepository agentRepository;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        System.out.println("Token requested for user: '{}' " + authentication.getName());
        String token = tokenService.generateToken(authentication);
        System.out.println("Token granted: {} " + token);

        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> createAgent(@RequestBody Agent agent) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = agent.getPassword();
            String username = agent.getUsername();
            String hashedPassword = passwordEncoder.encode(password);
            agent.setPassword(hashedPassword);
            agentRepository.save(agent);
            result.put("token",
                    tokenService.generateToken(new UsernamePasswordAuthenticationToken(username, password)));
            result.put("username", username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> login(@RequestBody Agent data) {
        String username = data.getUsername();
        String password = data.getPassword();
        HashMap<String, Object> result = new HashMap<>();
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Agent agent = agentRepository.findByUsername(username);
            Boolean compareResult = passwordEncoder.matches(password,
                    agent.getPassword());
            if (compareResult) {
                result.put("aid", agent.getId());
                result.put("token",
                        tokenService.generateToken(new UsernamePasswordAuthenticationToken(username,
                                password)));
                result.put("username", username);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("login status", "Failed");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return null;
    }

}
