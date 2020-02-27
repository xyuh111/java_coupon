package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户响应对象
 * Created by macro on CreateMerchantsResponse.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {
    /** 商户 id：创建失败为 -1 */
    private Integer id;
}
