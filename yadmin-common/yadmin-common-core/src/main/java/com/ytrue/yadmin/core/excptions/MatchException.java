package com.ytrue.yadmin.core.excptions;

import com.ytrue.yadmin.core.base.BaseCodeException;

/**
 * @author ytrue
 * @date 2022/4/20 17:40
 * @description 查询条件匹配异常
 */
public class MatchException extends BaseCodeException {

    public MatchException(String message) {
        super(message);
    }
}
