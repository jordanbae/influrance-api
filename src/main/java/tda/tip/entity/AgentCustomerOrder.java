package tda.tip.entity;

import java.math.BigInteger;
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
@Table(name = "agent_customer_order")
public class AgentCustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Long order_id;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    private Long product_id;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    private Long customer_id;

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    // private Long agent_id;

    // public Long getAgent_id() {
    // return agent_id;
    // }

    // public void setAgent_id(Long agent_id) {
    // this.agent_id = agent_id;
    // }

    private Long agentId;

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    private String product_name;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    private Integer product_price;

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    private Integer coverage_allowance;

    public Integer getCoverage_allowance() {
        return coverage_allowance;
    }

    public void setCoverage_allowance(Integer coverage_allowance) {
        this.coverage_allowance = coverage_allowance;
    }

    private Integer coverage_claimed;

    public Integer getCoverage_claimed() {
        return coverage_claimed;
    }

    public void setCoverage_claimed(Integer coverage_claimed) {
        this.coverage_claimed = coverage_claimed;
    }

    private Integer coverage_left;

    public Integer getCoverage_left() {
        return coverage_left;
    }

    public void setCoverage_left(Integer coverage_left) {
        this.coverage_left = coverage_left;
    }

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String citizen_id;

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    private String email;
    private String address;
    private String district;
    private String sub_district;
    private String postal_code;
    private String province;
    private Date birthdate;
    private Date start_date;
    private Date end_date;
    private String beneficiary_1_prefix;
    private String beneficiary_1_firstname;
    private String beneficiary_1_lastname;
    private String beneficiary_1_phone_number;
    private String beneficiary_1_relationship;
    private String beneficiary_2_prefix;
    private String beneficiary_2_firstname;
    private String beneficiary_2_lastname;
    private String beneficiary_2_phone_number;
    private String beneficiary_2_relationship;
    private String beneficiary_3_prefix;
    private String beneficiary_3_firstname;
    private String beneficiary_3_lastname;
    private String beneficiary_3_phone_number;
    private String beneficiary_3_relationship;
    private Date purchase_date;
    private String orderStatus;
    private Date created_at;
    private Date updated_at;

    public AgentCustomerOrder(Long order_id, Long product_id, Long customer_id, Long agent_id, String product_name,
            Integer product_price, Integer coverage_allowance, Integer coverage_claimed, Integer coverage_left,
            String prefix, String firstname, String lastname, String citizen_id, String email, String address,
            String district, String sub_district, String postal_code, String province, Date birthdate, Date start_date,
            Date end_date, String beneficiary_1_prefix, String beneficiary_1_firstname, String beneficiary_1_lastname,
            String beneficiary_1_phone_number, String beneficiary_1_relationship, String beneficiary_2_prefix,
            String beneficiary_2_firstname, String beneficiary_2_lastname, String beneficiary_2_phone_number,
            String beneficiary_2_relationship, String beneficiary_3_prefix, String beneficiary_3_firstname,
            String beneficiary_3_lastname, String beneficiary_3_phone_number, String beneficiary_3_relationship,
            Date purchase_date, String order_status) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.agentId = agent_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.coverage_allowance = coverage_allowance;
        this.coverage_claimed = coverage_claimed;
        this.coverage_left = coverage_left;
        this.prefix = prefix;
        this.firstname = firstname;
        this.lastname = lastname;
        this.citizen_id = citizen_id;
        this.email = email;
        this.address = address;
        this.district = district;
        this.sub_district = sub_district;
        this.postal_code = postal_code;
        this.province = province;
        this.birthdate = birthdate;
        this.start_date = start_date;
        this.end_date = end_date;
        this.beneficiary_1_prefix = beneficiary_1_prefix;
        this.beneficiary_1_firstname = beneficiary_1_firstname;
        this.beneficiary_1_lastname = beneficiary_1_lastname;
        this.beneficiary_1_phone_number = beneficiary_1_phone_number;
        this.beneficiary_1_relationship = beneficiary_1_relationship;
        this.beneficiary_2_prefix = beneficiary_2_prefix;
        this.beneficiary_2_firstname = beneficiary_2_firstname;
        this.beneficiary_2_lastname = beneficiary_2_lastname;
        this.beneficiary_2_phone_number = beneficiary_2_phone_number;
        this.beneficiary_2_relationship = beneficiary_2_relationship;
        this.beneficiary_3_prefix = beneficiary_3_prefix;
        this.beneficiary_3_firstname = beneficiary_3_firstname;
        this.beneficiary_3_lastname = beneficiary_3_lastname;
        this.beneficiary_3_phone_number = beneficiary_3_phone_number;
        this.beneficiary_3_relationship = beneficiary_3_relationship;
        this.purchase_date = purchase_date;
        this.orderStatus = order_status;
    }

    public AgentCustomerOrder() {
    }

    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getBeneficiary_1_prefix() {
        return beneficiary_1_prefix;
    }

    public void setBeneficiary_1_prefix(String beneficiary_1_prefix) {
        this.beneficiary_1_prefix = beneficiary_1_prefix;
    }

    public String getBeneficiary_1_firstname() {
        return beneficiary_1_firstname;
    }

    public void setBeneficiary_1_firstname(String beneficiary_1_firstname) {
        this.beneficiary_1_firstname = beneficiary_1_firstname;
    }

    public String getBeneficiary_1_lastname() {
        return beneficiary_1_lastname;
    }

    public void setBeneficiary_1_lastname(String beneficiary_1_lastname) {
        this.beneficiary_1_lastname = beneficiary_1_lastname;
    }

    public String getBeneficiary_1_phone_number() {
        return beneficiary_1_phone_number;
    }

    public void setBeneficiary_1_phone_number(String beneficiary_1_phone_number) {
        this.beneficiary_1_phone_number = beneficiary_1_phone_number;
    }

    public String getBeneficiary_1_relationship() {
        return beneficiary_1_relationship;
    }

    public void setBeneficiary_1_relationship(String beneficiary_1_relationship) {
        this.beneficiary_1_relationship = beneficiary_1_relationship;
    }

    public String getBeneficiary_2_prefix() {
        return beneficiary_2_prefix;
    }

    public void setBeneficiary_2_prefix(String beneficiary_2_prefix) {
        this.beneficiary_2_prefix = beneficiary_2_prefix;
    }

    public String getBeneficiary_2_firstname() {
        return beneficiary_2_firstname;
    }

    public void setBeneficiary_2_firstname(String beneficiary_2_firstname) {
        this.beneficiary_2_firstname = beneficiary_2_firstname;
    }

    public String getBeneficiary_2_lastname() {
        return beneficiary_2_lastname;
    }

    public void setBeneficiary_2_lastname(String beneficiary_2_lastname) {
        this.beneficiary_2_lastname = beneficiary_2_lastname;
    }

    public String getBeneficiary_2_phone_number() {
        return beneficiary_2_phone_number;
    }

    public void setBeneficiary_2_phone_number(String beneficiary_2_phone_number) {
        this.beneficiary_2_phone_number = beneficiary_2_phone_number;
    }

    public String getBeneficiary_2_relationship() {
        return beneficiary_2_relationship;
    }

    public void setBeneficiary_2_relationship(String beneficiary_2_relationship) {
        this.beneficiary_2_relationship = beneficiary_2_relationship;
    }

    public String getBeneficiary_3_prefix() {
        return beneficiary_3_prefix;
    }

    public void setBeneficiary_3_prefix(String beneficiary_3_prefix) {
        this.beneficiary_3_prefix = beneficiary_3_prefix;
    }

    public String getBeneficiary_3_firstname() {
        return beneficiary_3_firstname;
    }

    public void setBeneficiary_3_firstname(String beneficiary_3_firstname) {
        this.beneficiary_3_firstname = beneficiary_3_firstname;
    }

    public String getBeneficiary_3_lastname() {
        return beneficiary_3_lastname;
    }

    public void setBeneficiary_3_lastname(String beneficiary_3_lastname) {
        this.beneficiary_3_lastname = beneficiary_3_lastname;
    }

    public String getBeneficiary_3_phone_number() {
        return beneficiary_3_phone_number;
    }

    public void setBeneficiary_3_phone_number(String beneficiary_3_phone_number) {
        this.beneficiary_3_phone_number = beneficiary_3_phone_number;
    }

    public String getBeneficiary_3_relationship() {
        return beneficiary_3_relationship;
    }

    public void setBeneficiary_3_relationship(String beneficiary_3_relationship) {
        this.beneficiary_3_relationship = beneficiary_3_relationship;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    // public String getorder_status() {
    // return order_status;
    // }

    // public void setorder_status(String order_status) {
    // this.order_status = order_status;
    // }

    public String getorderStatus() {
        return orderStatus;
    }

    public void setorderStatus(String order_status) {
        this.orderStatus = order_status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
