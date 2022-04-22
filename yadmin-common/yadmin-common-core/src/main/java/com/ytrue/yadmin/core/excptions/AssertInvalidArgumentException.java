package com.ytrue.yadmin.core.excptions;

import com.ytrue.yadmin.core.base.BaseCodeException;
import com.ytrue.yadmin.core.enums.ResponseCode;

/**
 * @author ytrue
 * @date 2022/4/21 16:47
 * @description 断言匹配错误
 */
public class AssertInvalidArgumentException extends BaseCodeException {

    public AssertInvalidArgumentException(ResponseCode responseCode) {
        super(responseCode);
    }
}
