package com.ytrue.yadmin.oss.parameter.dto;

import com.ytrue.yadmin.oss.parameter.properties.AliyunProperties;
import com.ytrue.yadmin.oss.parameter.properties.LocalProperties;
import com.ytrue.yadmin.oss.parameter.properties.QcloudProperties;
import com.ytrue.yadmin.oss.parameter.properties.QiniuProperties;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/7/2 22:09
 * @description 引擎聚合
 */
@Data
public class Engine {


    private AliyunProperties aliyun;

    private QiniuProperties qiniu;

    private QcloudProperties qcloud;

    private LocalProperties local;
}
