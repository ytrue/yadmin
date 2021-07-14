package com.ytrue.yadmin.unified.dispose.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/7/8 10:35
 * @description 统一处理返回数据
 */
@Data
@ConfigurationProperties(prefix = "ytrue.unified.dispose.response-data")
public class UnifiedDisposeResponseDataProperties {

    /**
     * 是否开启
     */
    private Boolean enabled = true;

    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();


    /**
     * 统一返回url,后面新增正则匹配
     */
    private List<String> adviceFilterUrl = new ArrayList<>();
}
