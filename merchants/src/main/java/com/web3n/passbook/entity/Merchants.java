package com.web3n.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 商户对象模型
 * Created by macro on Merchants.
 **/
@Data
@NoArgsConstructor /** 无参构造函数 **/
@AllArgsConstructor /** 全参构造函数 **/
@Entity             /** 表明这是一个实体对象 **/
@Table(name = "merchants") //
public class Merchants {
    /** 商户id，主键 */
    @Id  /** 主键 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 不添加 strategy 属性保存时报错 com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'passbook.hibernate_sequence' doesn't exist */
//    @GeneratedValue /** 自动生成值，插入数据时不用配置 id 属性，保存到数据库会自动生成*/
    @Column(name="id", nullable = false) /** 列的属性 nullable false = not noll  */
    private Integer id;

    /** 商户名称 全局唯一的 */
    @Basic /** 默认属性，基本列，就算不加也会默认有， 相反的  @Transient 表示不属于这张表  */
    @Column(name = "name", unique = true, nullable = false) /** unique 全局唯一 */
    private String name;

    /** 商户logo */
    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    /** 商户营业执照 */
    @Basic
    @Column(name = "business_license_url", nullable = false)
    private String businessLicenseUrl;

    /** 商户的联系电话 */
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 商户地址 */
    @Basic
    @Column(name = "address", nullable = false)
    private String address;

    /** 商户是否通过审核 */
    @Basic
    @Column(name = "is_audit", nullable = false)
    private Boolean isAudit = false;
}
