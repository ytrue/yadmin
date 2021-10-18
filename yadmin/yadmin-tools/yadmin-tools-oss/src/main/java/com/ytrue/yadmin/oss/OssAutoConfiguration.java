package com.ytrue.yadmin.oss;

import com.ytrue.yadmin.oss.cloud.*;
import com.ytrue.yadmin.oss.cloud.QcloudCloud;
import com.ytrue.yadmin.oss.cloud.QiniuCloud;
import com.ytrue.yadmin.oss.properties.*;
import com.ytrue.yadmin.oss.util.OssUtil;
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
class OssAutoConfiguration {

    @Bean
    public OssUtil ossUtils(OssProperties ossProperties) {
        return new OssUtil(ossProperties);
    }

    @Bean
    public LocalCloud localUpload(LocalProperties localProperties) {
        return new LocalCloud(localProperties);
    }

    @Bean
    public AliyunCloud aliyunUpload(AliyunProperties aliyunProperties) {
        return new AliyunCloud(aliyunProperties);
    }

    @Bean
    public QcloudCloud qcloudUpload(QcloudProperties qcloudProperties) {
        return new QcloudCloud(qcloudProperties);
    }

    @Bean
    public QiniuCloud qiniuUpload(QiniuProperties qiniuProperties) {
        return new QiniuCloud(qiniuProperties);
    }
}
