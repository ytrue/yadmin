package com.ytrue.yadmin.core.annotation.handler;

import cn.hutool.core.util.ArrayUtil;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.ytrue.yadmin.core.annotation.ApiModelEnumProperty;
import com.ytrue.yadmin.core.enums.DefaultKeyValueEnum;
import com.ytrue.yadmin.core.enums.KeyValueEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ytrue
 * @date 2022/5/28 22:20
 * @description ApiModelEnumPropertyBuilder
 */
@Order(1)
@Primary
@Component
public class ApiModelEnumPropertyHandle implements ModelPropertyBuilderPlugin, ExpandedParameterBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        if (context.getAnnotatedElement().isPresent()) {
            ApiModelProperty mp = AnnotationUtils.getAnnotation(context.getAnnotatedElement().get(), ApiModelProperty.class);
            if (mp != null) {
                return;
            }
        }
        BeanPropertyDefinition bpd = context.getBeanPropertyDefinition().orElse(null);
        if (bpd == null) {
            return;
        }

        if (bpd.getField() == null) {
            return;
        }

        ApiModelEnumProperty ev = bpd.getField().getAnnotation(ApiModelEnumProperty.class);
        if (ev == null) {
            return;
        }
        //处理了
        ModelPropertyBuilder builder = context.getBuilder();
        builder.description(ev.value());
        builder.required(ev.required());

        // 获取字段类型
        Class<?> rawType = bpd.getField().getRawType();
        // 是否为枚举类型，为枚举类型，对应的value需为枚举值的名称。而不是调用getValue方法获取值，因为枚举类型前端需传枚举值对象名才可以转换为枚举对象
        boolean isEnumType = KeyValueEnum.class.isAssignableFrom(rawType);

        AllowableListValues values = getAllowableListValues(rawType, ev);

        // 如果字段类型不为枚举类型且不为字符串，则将枚举值信息增加至描述信息中
        if (!isEnumType && rawType != String.class) {
            builder.description(ev.value() + ",可用值:" + String.join(",", values.getValues()));
        } else {
            builder.allowableValues(values);
        }

    }

    @Override
    public void apply(ParameterExpansionContext context) {
        ApiModelEnumProperty apiModelEnumProperty = context.findAnnotation(ApiModelEnumProperty.class).orElse(null);
        if (apiModelEnumProperty == null) {
            return;
        }
        AllowableListValues allowableListValues = getAllowableListValues(context.getFieldType().getErasedType(), apiModelEnumProperty);
        context.getParameterBuilder().required(apiModelEnumProperty.required())
                .description(apiModelEnumProperty.value() + ",可用值-:" + String.join(", ", allowableListValues.getValues()));
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

    /**
     * 获取允许的枚举值
     *
     * @param fieldType            字段类型
     * @param apiModelEnumProperty 注解信息
     * @return
     */
    @SuppressWarnings("all")
    private AllowableListValues getAllowableListValues(Class<?> fieldType, ApiModelEnumProperty apiModelEnumProperty) {

        // 是否为枚举类型，为枚举类型，对应的value需为枚举值的名称。而不是调用getValue方法获取值，因为枚举类型前端需传枚举值对象名才可以转换为枚举对象
        boolean isEnumType = KeyValueEnum.class.isAssignableFrom(fieldType);

        Class<?> enumClass = apiModelEnumProperty.enumClass();

        //如果是NameValueEnum类型，并且是默认值
        if (isEnumType && enumClass == DefaultKeyValueEnum.class) {
            enumClass = fieldType;
        }


        KeyValueEnum[] enumConstants = (KeyValueEnum[]) enumClass.getEnumConstants();

        String[] vs = apiModelEnumProperty.values();
        // 如果vs空，则为values中指定枚举值
        if (ArrayUtil.isEmpty(vs)) {
            List<String> values = Arrays.stream(enumConstants)
                    .map(nameValueEnum -> getPair(nameValueEnum.getKey().toString(), nameValueEnum.getValue().toString()))
                    .collect(Collectors.toList());


            return new AllowableListValues(values, enumClass.getName());
        }


        List<String> vl = new ArrayList<>();

        for (KeyValueEnum ce : enumConstants) {
            if (ArrayUtil.contains(vs, ce.toString())) {
                vl.add(getPair(ce.getKey().toString(), ce.getValue().toString()));
            }
        }
        return new AllowableListValues(vl, enumClass.getName());

    }

    private String getPair(String code, String name) {
        return code + ":" + name;
    }


}
