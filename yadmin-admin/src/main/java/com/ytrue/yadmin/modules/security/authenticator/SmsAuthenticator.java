package com.ytrue.yadmin.modules.security.authenticator;

import com.ytrue.yadmin.modules.sys.model.SysUser;
import com.ytrue.yadmin.modules.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.yadmin.modules.security.integration.authenticator.AbstractPreparableIntegrationAuthenticator;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 短信认证器--这个待保留
 */
@Component
public class SmsAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    private final static String AUTH_TYPE = "sms";

    /*@Autowired //暂时注释，不处理这个
    private UserMapper mapper;*/

    /**
     * 预处理
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public SysUser authenticate(IntegrationAuthenticationEntity entity) {

        String mobile = entity.getAuthParameter("mobile");
        if (StringUtils.isEmpty(mobile)) {
            throw new OAuth2Exception("手机号不能为空");
        }
        String code = entity.getAuthParameter("code");
        //测试项目，所以将验证码顶死为：1234
        if (!"1234".equals(code)) {
            throw new OAuth2Exception("验证码错误或已过期");
        }
        return null;
    }

    /**
     * 认证结束后执行
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
