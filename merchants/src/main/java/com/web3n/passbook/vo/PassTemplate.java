package com.web3n.passbook.vo;

import com.web3n.passbook.constant.ErrorCode;
import com.web3n.passbook.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 投放的优惠劵对象定义
 * Created by macro on PassTemplate.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
    /** 所属商户 id */
    private Integer id;

    /** 优惠劵标题 */
    private String title;

    /** 优惠劵摘要 */
    private String summary;

    /** 优惠劵详细信息 */
    private String desc;

    /** 优惠劵 Token，用于商户核销 */
    private Boolean hasToken; // token 存储于 Redis Set 中，每次领取从 Redis 中获取

    /** 优惠劵背景色 */
    private Integer background;

    /** 优惠劵开始时间 */
    private Date start;

    /** 优惠劵结束时间 */
    private Date end;

    /**
     * 校验优惠劵的有效性
     * @param merchantsDao
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(null == merchantsDao.findById(id)){
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
