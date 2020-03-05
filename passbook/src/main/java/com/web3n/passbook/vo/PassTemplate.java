package com.web3n.passbook.vo;

import com.web3n.passbook.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by macro on PassTemplate.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
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
    /** Result 转  PassTemplate*/
    public static PassTemplate toPassTemplate(Result result) throws ParseException {
        byte[] FAMILY_B = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B);
        byte[] ID = Bytes.toBytes(Constants.PassTemplateTable.ID);
        byte[] TITLE = Bytes.toBytes(Constants.PassTemplateTable.TITLE);
        byte[] SUMMARY = Bytes.toBytes(Constants.PassTemplateTable.SUMMARY);
        byte[] DESC = Bytes.toBytes(Constants.PassTemplateTable.DESC);
        byte[] HAS_TOKEN = Bytes.toBytes(Constants.PassTemplateTable.HAS_TOKEN);
        byte[] BACKGROUND = Bytes.toBytes(Constants.PassTemplateTable.BACKGROUND);
        
        byte[] FAMILY_C = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C);
        byte[] LIMIT = Bytes.toBytes(Constants.PassTemplateTable.LIMIT);
        byte[] START = Bytes.toBytes(Constants.PassTemplateTable.START);
        byte[] END = Bytes.toBytes(Constants.PassTemplateTable.END);
        
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(Bytes.toInt(result.getValue(FAMILY_B, ID)));
        passTemplate.setTitle(Bytes.toString(result.getValue(FAMILY_B, TITLE)));
        passTemplate.setSummary(Bytes.toString(result.getValue(FAMILY_B, SUMMARY)));
        passTemplate.setDesc(Bytes.toString(result.getValue(FAMILY_B, DESC)));
        passTemplate.setHasToken(Bytes.toBoolean(result.getValue(FAMILY_B, HAS_TOKEN)));
        passTemplate.setBackground(Bytes.toInt(result.getValue(FAMILY_B, BACKGROUND)));
        
        String[] patterns = new String[] {"yyyy-MM-dd"};
        
        passTemplate.setLimit(Bytes.toLong(result.getValue(FAMILY_C, LIMIT)));
        passTemplate.setStart(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C, START)), patterns));
        passTemplate.setEnd(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C, END)), patterns));
        return passTemplate;
    }
    
}
