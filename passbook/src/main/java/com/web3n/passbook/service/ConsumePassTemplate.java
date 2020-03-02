package com.web3n.passbook.service;

import com.alibaba.fastjson.JSON;
import com.web3n.passbook.constant.Constants;
import com.web3n.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by macro on ConsumePassTemplate.
 **/
@Slf4j
@Component
public class ConsumePassTemplate {
    /** pass 相关的 HBase 服务 */
    @Autowired
    public IHBasePassService passService;

    /**
     *
     * @param passTemplate
     * @param key
     * @param partition
     * @param topic
     */
    @KafkaListener(topics = {Constants.TEMPLATE_TOPIC})
    public void receive(@Payload String passTemplate,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("Consumer Receive PassTemplate: {}", passTemplate);
        PassTemplate pt;
        try{
            pt = JSON.parseObject(passTemplate, PassTemplate.class);
        } catch(Exception ex){
            log.error("Parse PassTemplate Error: {}", ex.getMessage());
            return;
        }
        log.info("DropPassTemplateToHBase: {}", passService.dropPassTemplateToHBase(pt));
    }
}
