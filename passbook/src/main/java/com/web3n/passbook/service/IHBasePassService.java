package com.web3n.passbook.service;

import com.web3n.passbook.vo.PassTemplate;

/**
 * Created by macro on IHBasePassService.
 * Pass HBase 服务
 **/
public interface IHBasePassService {
    /**
     * 将 PassTemplate 写入 HBase
     * @param passTemplate {@link PassTemplate}
     * @return true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
