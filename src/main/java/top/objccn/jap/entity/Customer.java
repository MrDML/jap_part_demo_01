package top.objccn.jap.entity;

import javax.persistence.*;

/**
 * @Auter MrDML
 * @Date 2019-11-08
 */

// 代表是一个jpa的实体类
@Entity
// 配置实体类和数据库中标的映射关系 name 对应的表名
@Table(name = "cst_customer")
public class Customer {

    // 配置主键的生成策略
    // GenerationType.IDENTITY 是自增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 使用序列生成主键
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cust_seq")
    //@SequenceGenerator(name = "cust_seq",sequenceName = "cust_sequence")
    //使用表生成主键
    //@GeneratedValue(strategy = GenerationType.TABLE,generator = "tab_gen")
    //@TableGenerator(name = "tab_gen",table = "ids_gen",pkColumnName = "ids",valueColumnName = "vals", pkColumnValue =
    //        "customer",allocationSize = 1)


    //在主键字段使用@Id标注
    @Id
    @Column(name = "cust_id")
    private long custId;

    // 配置属性和字段的映射关系
    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_phone")
    private String custPhone;


    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" + "custId=" + custId + ", custName='" + custName + '\'' + ", custSource='" + custSource + '\'' + ", custIndustry='" + custIndustry + '\'' + ", custLevel='" + custLevel + '\'' + ", custAddress='" + custAddress + '\'' + ", custPhone='" + custPhone + '\'' + '}';
    }
}
