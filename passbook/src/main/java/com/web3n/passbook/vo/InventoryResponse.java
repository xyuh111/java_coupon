package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by macro on InventoryResponse.
 * 库存请求响应
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    /** 用户 id */
    private Long userId;
    /** 用户劵模板信息 */
    private List<PassTemplateInfo> passTemplateInfos;

}
