package com.web3n.passbook.vo;

import com.web3n.passbook.constant.ErrorCode;
import com.web3n.passbook.dao.MerchantsDao;
import com.web3n.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户请求对象
 * Created by macro on CreateMerchantsRequest.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {
    /** 商户名称 */
    private String name;

    /** 商户 logo */
    private String logoUrl;

    /** 商户营业执照 */
    private String businessLicenseUrl;

    /** 商户联系电话 */
    private String phone;

    /** 商户地址 */
    private String address;

    /**
     * 验证请求的有效性
     * @param merchantsDao
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(null == merchantsDao.findByName(name)){
            return ErrorCode.DUPLICATE_NAME;
        };
        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }
        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }
        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }
        if(null == this.phone){
            return ErrorCode.ERROR_PHONE;
        }
        return ErrorCode.SUCCESS;
    }
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();
        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setPhone(phone);
        merchants.setAddress(address);
        return merchants ;
    }
}
