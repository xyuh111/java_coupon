package com.web3n.passbook.constant;

/**
 * Created by macro on FeedbackType.
 * 评论类型枚举
 **/
public enum FeedbackType {
    PASS(1, "针对优惠劵的评论"),
    APP(2, "针对卡包 APP 的评论");

    /** 评论类型编码 */
    private Integer code;

    /** 评论类型描述 */
    private String desc;

    FeedbackType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
