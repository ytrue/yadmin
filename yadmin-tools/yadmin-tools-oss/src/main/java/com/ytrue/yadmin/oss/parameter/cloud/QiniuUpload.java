package com.ytrue.yadmin.oss.parameter.cloud;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import cn.hutool.core.convert.Convert;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.oss.parameter.properties.BaseProperties;
import com.ytrue.yadmin.oss.parameter.properties.QiniuProperties;
import com.ytrue.yadmin.oss.parameter.enums.UploadType;
import com.ytrue.yadmin.oss.parameter.factory.UploadFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 七牛云
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class QiniuUpload extends AbstractUpload {

    private QiniuProperties qiniuProperties;

    @Override
    public String upload(byte[] data, String fileName) {
        return upload(qiniuProperties, data, fileName);
    }

    @Override
    public String upload(BaseProperties properties, byte[] data, String fileName) {
        try {
            QiniuProperties properties1 = Convert.convert(QiniuProperties.class, properties);

            Configuration cfg = new Configuration(Region.huanan());
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(
                    properties1.getAccessKey(),
                    properties1.getSecretKey()
            );
            String upToken = auth.uploadToken(properties1.getBucket());
            Response response = uploadManager.put(data, fileName, upToken);
            if (!response.isOK()) {
                throw new RuntimeException("七牛上传出错：" + response.toString());
            }
            return properties1.getDomain() + "/" + fileName;
        } catch (Exception e) {
            throw new YadminException("七牛上传文件失败" + e.getMessage());
        }
    }

    /**
     * 注册
     */
    @Override
    public void afterPropertiesSet() {
        UploadFactory.register(UploadType.QINIU.getType(), this);
    }
}
