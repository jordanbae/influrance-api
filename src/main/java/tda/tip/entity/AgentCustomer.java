package tda.tip.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@Table(name = "agent_customer")
public class AgentCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // @Column(name = "agent_id")
    // private Long agent_id;

    private Long agentId;

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // public Long getAgent_id() {
    // return agent_id;
    // }

    // public void setAgent_id(Long agent_id) {
    // this.agent_id = agent_id;
    // }

    private Long customer_id;

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    private String customer_prefix;

    public String getCustomer_prefix() {
        return customer_prefix;
    }

    public void setCustomer_prefix(String customer_prefix) {
        this.customer_prefix = customer_prefix;
    }

    private String customer_firstname;

    public String getCustomer_firstname() {
        return customer_firstname;
    }

    public void setCustomer_firstname(String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }

    private String customer_lastname;

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private Date updated;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Column(unique = true)
    private String customer_email;

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    private String customer_phone_number;

    public String getCustomer_phone_number() {
        return customer_phone_number;
    }

    public void setCustomer_phone_number(String customer_phone_number) {
        this.customer_phone_number = customer_phone_number;
    }

    public AgentCustomer(Long agent_id, Long customer_id, String customer_prefix, String customer_firstname,
            String customer_lastname, String customer_email, String customer_phone_number) {
        this.agentId = agent_id;
        this.customer_id = customer_id;
        this.customer_prefix = customer_prefix;
        this.customer_firstname = customer_firstname;
        this.customer_lastname = customer_lastname;
        this.customer_email = customer_email;
        this.customer_phone_number = customer_phone_number;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public AgentCustomer() {

    }

}
