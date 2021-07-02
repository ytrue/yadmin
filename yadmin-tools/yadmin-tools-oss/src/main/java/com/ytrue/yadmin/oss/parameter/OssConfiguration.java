package com.ytrue.yadmin.oss.parameter;

import com.ytrue.yadmin.oss.parameter.cloud.AliyunUpload;
import com.ytrue.yadmin.oss.parameter.cloud.LocalUpload;
import com.ytrue.yadmin.oss.parameter.cloud.QcloudUpload;
import com.ytrue.yadmin.oss.parameter.cloud.QiniuUpload;
import com.ytrue.yadmin.oss.parameter.properties.*;
import com.ytrue.yadmin.oss.parameter.utils.OssUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ytrue
 * @date 2021/7/2 09:38
 * @description 配置
 */
@EnableConfigurationProperties({
        OssProperties.class,
        LocalProperties.class,
        AliyunProperties.class,
        QcloudProperties.class,
        QiniuProperties.class
})
class OssConfiguration {

    @Bean
    public OssUtils ossUtils(OssProperties ossProperties) {
        return new OssUtils(ossProperties);
    }

    @Bean
    public LocalUpload localUpload(LocalProperties localProperties) {
        return new LocalUpload(localProperties);
    }

    @Bean
    public AliyunUpload aliyunUpload(AliyunProperties aliyunProperties) {
        return new AliyunUpload(aliyunProperties);
    }

    @Bean
    public QcloudUpload qcloudUpload(QcloudProperties qcloudProperties) {
        return new QcloudUpload(qcloudProperties);
    }

    @Bean
    public QiniuUpload qiniuUpload(QiniuProperties qiniuProperties) {
        return new QiniuUpload(qiniuProperties);
    }
}
