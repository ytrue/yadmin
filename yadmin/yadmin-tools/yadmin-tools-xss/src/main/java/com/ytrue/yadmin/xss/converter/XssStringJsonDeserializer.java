package com.ytrue.yadmin.xss.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ytrue.yadmin.xss.utils.XssUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/30 17:29
 * @description 过滤跨站脚本的 反序列化工具
 */
public class XssStringJsonDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext dc) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            String value = p.getValueAsString();

            if (value == null || "".equals(value)) {
                return value;
            }

            List<String> list = new ArrayList<>();
            list.add("<script>");
            list.add("</script>");
            list.add("<iframe>");
            list.add("</iframe>");
            list.add("<noscript>");
            list.add("</noscript>");
            list.add("<frameset>");
            list.add("</frameset>");
            list.add("<frame>");
            list.add("</frame>");
            list.add("<noframes>");
            list.add("</noframes>");
            boolean flag = list.stream().anyMatch(value::contains);
            if (flag) {
                return XssUtils.xssClean(value, null);
            }
            return value;
        }
        return null;
    }
}
