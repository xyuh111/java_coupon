package com.web3n.passbook.constant;

/**
 * Created by macro on PassStatus.
 * 优惠劵的状态
 **/
public enum PassStatus {
    UNUSED(1, "未被使用的"),
    USED(2, "已经使用的"),
    ALL(3, "全部领取的");
    /** 状态码 */
    private Integer code;
    /** 状态描述 */
    private String desc;

    PassStatus(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }
}
