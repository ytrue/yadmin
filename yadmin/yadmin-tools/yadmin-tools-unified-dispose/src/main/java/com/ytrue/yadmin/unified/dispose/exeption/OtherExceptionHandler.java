package com.ytrue.yadmin.unified.dispose.exeption;


import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.unified.dispose.properties.UnifiedDisposeExceptionProperties;
import com.ytrue.yadmin.util.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author ytrue
 * @date 2021/7/14 11:35
 * @description 其他验证异常
 */
@ResponseBody
public class OtherExceptionHandler {


    @Autowired
    private UnifiedDisposeExceptionProperties unifiedDisposeExceptionProperties;

    /**
     * 验证异常处理 主要针对 @Validated 和 Valid的错误处理
     *
     * @param ex
     * @return
     */
    @SneakyThrows
    @ExceptionHandler({
            //这个暂时不明确
            ConstraintViolationException.class,
            //这个没有加@RequestBody抛出的异常
            BindException.class,
            //这个是加了RequestBody抛出的异常
            MethodArgumentNotValidException.class
    })
    public R<ArrayList<String>> validateException(Exception ex) {
        errorDispose(ex);
        ArrayList<String> list = new ArrayList<>();
        if (ex instanceof ConstraintViolationException) {

            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

            for (ConstraintViolation<?> violation : violations) {
                list.add(violation.getMessage());
            }

        } else if (ex instanceof BindException) {

            BindException bindException = (BindException) ex;
            BindingResult bindingResult = bindException.getBindingResult();
            list = getErrorList(bindingResult);

        } else if (ex instanceof MethodArgumentNotValidException) {

            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            list = getErrorList(bindingResult);
        }
        return R.fail(ExceptionCode.ARGUMENT_NOT_VALID, list);
    }

    /**
     * 是否开启全局异常处理
     *
     * @param e
     * @param <T>
     * @throws Throwable
     */
    private <T extends Throwable> void errorDispose(T e) throws Throwable {
        if (!unifiedDisposeExceptionProperties.getEnabled()) {
            throw e;
        }
    }

    /**
     * 获得错误集合
     *
     * @param bindingResult
     * @return
     */
    private ArrayList<String> getErrorList(BindingResult bindingResult) {
        ArrayList<String> errorList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> errorList.add(fieldError.getDefaultMessage()));
        }
        return errorList;
    }
}
