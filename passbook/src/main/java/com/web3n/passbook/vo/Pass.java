package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by macro on Pass.
 * 用户领取的优惠劵
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pass {
    /** 用户 id */
    private Long userId;
    /** pass 在 HBase 中的 RowKey */
    private String rowKey;
    /** PassTemplate 在 HBase 中的 RowKey */
    private String templateId;
    /** 优惠劵 token，有可能是 null ，则填充 -1*/
    private String token;
    /** 领取日期 */
    private Date assignedDate;
    /** 消费日期，不为空代表以经被消费了 */
    private Date conDate;
}
