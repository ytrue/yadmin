package com.ytrue.yadmin.oss.parameter.utils;

import com.ytrue.yadmin.oss.parameter.properties.OssProperties;
import com.ytrue.yadmin.oss.parameter.factory.UploadFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * @author ytrue
 * @date 2021/7/2 14:45
 * @description UploadUtils
 */
@AllArgsConstructor
@NoArgsConstructor
@EnableConfigurationProperties({OssProperties.class})
public class OssUtils {

    private OssProperties ossProperties;

    /**
     * 上传
     *
     * @param b
     * @param fileName
     * @return
     */
    public String upload(byte[] b, String fileName) {
        return UploadFactory.getInvokeStrategy(ossProperties.getEngine()).upload(b, fileName);
    }
}
