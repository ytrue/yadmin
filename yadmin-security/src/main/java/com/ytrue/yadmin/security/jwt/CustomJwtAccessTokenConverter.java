package com.ytrue.yadmin.security.jwt;


import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description jwt信息扩展
 */
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //这里暂时不做jwt增强处理
//        User user = (User) authentication.getPrincipal();
//        Map<String, Object> additionalInformation = new LinkedHashMap<>();
//        additionalInformation.put("avatar", user.getImages());
//        additionalInformation.put("userId", user.getUserId());
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(accessToken, authentication);
    }
}
