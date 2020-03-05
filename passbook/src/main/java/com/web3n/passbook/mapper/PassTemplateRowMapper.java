package com.web3n.passbook.mapper;

import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import com.web3n.passbook.vo.PassTemplate;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by macro on PassTemplateRowMapper.
 * HBase PassTemplate Row To PassTemplate Object
 **/
public class PassTemplateRowMapper implements RowMapper<PassTemplate> {
    @Override
    public PassTemplate mapRow(Result result, int i) throws Exception {
        PassTemplate passTemplate = PassTemplate.toPassTemplate(result);
        return passTemplate;
    }
}
