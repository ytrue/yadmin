package com.ytrue.yadmin.oss.parameter.utils;


import com.ytrue.yadmin.oss.parameter.dto.UploadSetting;
import com.ytrue.yadmin.oss.parameter.factory.UploadFactory;
import com.ytrue.yadmin.oss.parameter.properties.OssProperties;
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
     * @param setting
     * @param b
     * @param fileName
     * @return
     */
    public String upload(UploadSetting setting, byte[] b, String fileName) {
        return UploadFactory.getInvokeStrategy(setting.getDefaultEngine()).upload(setting.getEngine(), b, fileName);
    }

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
