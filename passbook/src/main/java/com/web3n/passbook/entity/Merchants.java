package com.web3n.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by macro on Merchants.
 * 商户对象模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    /** 商户名称 */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /** 商户 logo */
    @Basic
    @Column(name = "logo_url", nullable = false)
    public String logoUrl;

    /** 商户营业执照 */
    @Basic
    @Column(name = "business_license_url", nullable = false)
    public String businessLicenseUrl;

    /** 商户联系电话  */
    @Basic
    @Column(name = "phone", nullable = false)
    public String phone;

    /** 商户地址 */
    @Basic
    @Column(name = "address", nullable = false)
    public String address;

    /** 商户是否通过审核 */
    @Basic
    @Column(name = "is_audit", nullable = false)
    public String isAudit;
}
