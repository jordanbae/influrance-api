package tda.tip.Repository;

import org.springframework.stereotype.Repository;
import tda.tip.entity.AgentCustomer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentCustomerRepository extends JpaRepository<AgentCustomer, Long> {

    List<AgentCustomer> findByAgentId(Long agent_id);
}
