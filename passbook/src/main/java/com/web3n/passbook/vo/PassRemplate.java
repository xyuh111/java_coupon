package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by macro on PassRemplate.
 * 投放的优惠劵定义
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassRemplate {
    /** 所属商户 id */
    private Integer id;
    /** 优惠劵标题 */
    private String title;
    /** 优惠劵摘要 */
    private String summary;
    /** 优惠劵详细信息 */
    private String desc;
    /** 最大个数限制 */
    private Long limit;
    /** 优惠劵是否有 Token，用于商户核销 */
    private Boolean hasToken;
    /** 优惠劵背景色 */
    private Integer background;
    /** 优惠劵开始时间 */
    private Date start;
    /** 优惠劵结束时间 */
    private Date end;
}
