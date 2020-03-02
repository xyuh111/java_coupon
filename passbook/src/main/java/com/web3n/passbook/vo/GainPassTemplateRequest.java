package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by macro on GainPassTemplateRequest.
 * 用户领取优惠劵的请求对象
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GainPassTemplateRequest {
    /** 用户 id */
    private Long userId;
    /** PassTemplate 对象 */
    private PassTemplate passTemplate;
}
