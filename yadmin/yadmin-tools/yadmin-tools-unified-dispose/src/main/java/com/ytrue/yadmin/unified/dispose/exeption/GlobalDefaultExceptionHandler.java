package com.ytrue.yadmin.unified.dispose.exeption;


import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.unified.dispose.annotation.IgnoreResponseAdvice;
import com.ytrue.yadmin.unified.dispose.properties.UnifiedDisposeExceptionProperties;
import com.ytrue.yadmin.utils.R;
import com.ytrue.yadmin.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description 全局异常处理类, 照搬BasicErrorController，在此基础上增加自己的处理
 */
@Slf4j
@Controller
@IgnoreResponseAdvice
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class GlobalDefaultExceptionHandler extends AbstractErrorController {

    @Autowired
    private ErrorProperties errorProperties;

    @Autowired
    private UnifiedDisposeExceptionProperties unifiedDisposeExceptionProperties;

    @Autowired
    private ErrorAttributes errorAttributes;

    public GlobalDefaultExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return null;
    }


    /**
     * 这个要定制我的处理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(
            HttpServletRequest request,
            HttpServletResponse response,
            WebRequest req
    ) {
        if (unifiedDisposeExceptionProperties.getEnabled()) {
            response.setStatus(ExceptionCode.SUCCESS.getCode());
            ResponseUtils.renderJson(response, myHandle(response, req));
            return null;
        }
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        return modelAndView != null ? modelAndView : new ModelAndView("error", model);
    }


    /**
     * 这个要定制我的处理
     *
     * @param request
     * @return
     */
    @RequestMapping
    public ResponseEntity<Object> error(
            HttpServletResponse response,
            HttpServletRequest request,
            WebRequest req
    ) {
        if (unifiedDisposeExceptionProperties.getEnabled()) {
            response.setStatus(ExceptionCode.SUCCESS.getCode());
            ResponseUtils.renderJson(response, myHandle(response, req));
            return null;
        }

        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity(status);
        } else {
            Map<String, Object> body = this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL));
            return new ResponseEntity(body, status);
        }
    }


    /**
     * 定制处理
     *
     * @param response
     * @param req
     */
    private R<Object> myHandle(HttpServletResponse response, WebRequest req) {
//        if (unifiedDisposeExceptionProperties.getProduction()) {
//            ExceptionCode codeByExceptionCode = ExceptionCode.getCodeByExceptionCode(response.getStatus());
//            return R.fail(codeByExceptionCode);
//        }

        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                ));

        String message = (String) errorAttributes.get("message");
        //针对oauth2登录失败处理
        Throwable error = this.errorAttributes.getError(req);
        if (error instanceof InternalAuthenticationServiceException) {
            message = error.getMessage();
        }
        return R.
                fail((Integer) errorAttributes.get("status"), message);
    }


    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<String> mediaTypeNotAcceptable(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return ResponseEntity.status(status).build();
    }

    protected ErrorAttributeOptions getErrorAttributeOptions(HttpServletRequest request, MediaType mediaType) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        if (this.errorProperties.isIncludeException()) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.EXCEPTION});
        }

        if (this.isIncludeStackTrace(request, mediaType)) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.STACK_TRACE});
        }

        if (this.isIncludeMessage(request, mediaType)) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.MESSAGE});
        }

        if (this.isIncludeBindingErrors(request, mediaType)) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.BINDING_ERRORS});
        }

        return options;
    }


    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeStacktrace()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
            case ON_TRACE_PARAM:
                return this.getTraceParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeMessage(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeMessage()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getMessageParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeBindingErrors(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeBindingErrors()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getErrorsParameter(request);
            default:
                return false;
        }
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
