package com.ytrue.yadmin.security.error;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.ytrue.yadmin.security.exeption.CustomOauthException;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.Map;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 认证服务器错误数据返回格式
 */
@Slf4j
public class YadminOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public YadminOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeStringField("code", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("message", value.getMessage());
        gen.writeStringField("data", null);

        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
