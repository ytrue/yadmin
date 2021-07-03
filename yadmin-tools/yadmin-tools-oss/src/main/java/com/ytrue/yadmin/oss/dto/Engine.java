package com.ytrue.yadmin.oss.dto;

import com.ytrue.yadmin.oss.properties.AliyunProperties;
import com.ytrue.yadmin.oss.properties.LocalProperties;
import com.ytrue.yadmin.oss.properties.QcloudProperties;
import com.ytrue.yadmin.oss.properties.QiniuProperties;
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
