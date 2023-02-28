package tda.tip.Repository;

import org.springframework.stereotype.Repository;
import tda.tip.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    @Query(value = "SELECT * FROM agent WHERE username = ?1 ", nativeQuery = true)
    public Agent findByUsername(String username);

    @Query(value = "SELECT * FROM agent WHERE id = ?1 ", nativeQuery = true)
    public Agent findByID(int id);
}
