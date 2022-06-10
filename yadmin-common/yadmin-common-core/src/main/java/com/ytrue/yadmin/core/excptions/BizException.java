package com.ytrue.yadmin.core.excptions;

import com.ytrue.yadmin.core.base.BaseCodeException;
import com.ytrue.yadmin.core.base.BaseExceptionCode;

/**
 * @author ytrue
 * @date 2022/5/28 10:10
 * @description BizException 业务异常
 */
public class BizException extends BaseCodeException {


    private static final long serialVersionUID = 1922140199999930024L;

    public BizException(String message) {
        super(message);
    }

    public BizException(BaseExceptionCode responseCode) {
        super(responseCode);
    }

}
