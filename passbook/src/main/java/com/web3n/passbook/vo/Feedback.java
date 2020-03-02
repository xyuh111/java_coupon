package com.web3n.passbook.vo;

import com.google.common.base.Enums;
import com.web3n.passbook.constant.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by macro on Feedback.
 * 用户评论表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    /** 用户 id */
    private Long userId;
    /** 评论类型 */
    private String type;
    /** PassTemplate RowKey，如果是 app 类型的评论，则没有*/
    private String templateId;
    /** 评论内容 */
    private String comment;
    public boolean validate(){
        /** type 转大写，如果 feedbackType 枚举中存在 type 便返回这个值，否则返回 null */
        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class, this.type.toUpperCase()
        ).orNull();
        return (null != feedbackType && null != comment);
    }
}
