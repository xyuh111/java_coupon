package com.web3n.passbook.service;

import com.web3n.passbook.vo.Feedback;
import com.web3n.passbook.vo.Response;

/**
 * Created by macro on IFeedbackService.
 * 评论功能：用户评论相关功能实现
 **/
public interface IFeedbackService {
    /**
     * 创建评论
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response createFeedback(Feedback feedback);

    /**
     * 获取用户评论
     * @param userId 用户 id
     * @return {@link Response}
     */
    Response getFeedback(Long userId);
}
