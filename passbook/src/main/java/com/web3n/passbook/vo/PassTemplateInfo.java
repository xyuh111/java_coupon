package com.web3n.passbook.vo;

import com.web3n.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.processing.SupportedAnnotationTypes;

/**
 * Created by macro on PassTemplateInfo.
 * 优惠劵模板信息
 **/
@Data /** 此注解包含 @EqualsAndHashCode */
@EqualsAndHashCode(callSuper = true) /** 解决主键在父类的 equals 判断问题 */
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplateInfo extends PassTemplate {
    /** 优惠劵模板 */
    private PassTemplate passTemplate;
    /** 优惠劵对应的商户 */
    private Merchants merchants;
}
