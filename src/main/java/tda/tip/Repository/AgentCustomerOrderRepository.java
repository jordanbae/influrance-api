package tda.tip.Repository;

import org.springframework.stereotype.Repository;
import tda.tip.entity.AgentCustomerOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentCustomerOrderRepository extends JpaRepository<AgentCustomerOrder, Integer> {
    List<AgentCustomerOrder> findByAgentIdAndOrderStatus(Long agent_id, String orderStatus);

    Optional<AgentCustomerOrder> findById(Integer id);
}
