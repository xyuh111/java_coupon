package com.web3n.passbook.vo;

import com.web3n.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by macro on PassInfo.
 * 用户领取的优惠劵信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassInfo {
    /** 优惠劵 */
    private Pass pass;
    /** 优惠劵模板 */
    private PassTemplate passTemplate;
    /** 优惠劵对应的商户 */
    private Merchants merchants;
}
