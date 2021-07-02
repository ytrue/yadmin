package com.ytrue.yadmin.oss.parameter.cloud;

import cn.hutool.core.convert.Convert;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.oss.parameter.properties.BaseProperties;
import com.ytrue.yadmin.oss.parameter.properties.QcloudProperties;
import com.ytrue.yadmin.oss.parameter.enums.UploadType;
import com.ytrue.yadmin.oss.parameter.factory.UploadFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 腾讯云
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class QcloudUpload extends AbstractUpload {

    private QcloudProperties qcloudProperties;

    @Override
    public String upload(byte[] data, String fileName) {
        return upload(qcloudProperties, data, fileName);
    }

    @Override
    public String upload(BaseProperties properties, byte[] data, String fileName) {
        QcloudProperties properties1 = Convert.convert(QcloudProperties.class, properties);
        try {
            // 存储目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dir = properties1.getFileHost() + "/" + sdf.format(new Date());
            String path = dir + "/" + fileName;

            //初始化用户身份信息
            COSCredentials cosCredentials = new BasicCOSCredentials(
                    properties1.getSecretId(),
                    properties1.getSecretKey()
            );

            //设置bucket区域，
            //clientConfig中包含了设置region
            ClientConfig clientConfig = new ClientConfig(
                    new Region(properties1.getRegion())
            );
            //生成cos客户端
            COSClient cosClient = new COSClient(cosCredentials, clientConfig);
            // 指定要上传到 COS 上对象键
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    properties1.getBucket(),
                    path,
                    new ByteArrayInputStream(data),
                    null
            );
            cosClient.putObject(putObjectRequest);

            return properties1.getDomain() +
                    "/" +
                    path;

        } catch (Exception e) {
            throw new YadminException("腾讯云上传文件失败" + e.getMessage());
        }
    }

    /**
     * 注册
     */
    @Override
    public void afterPropertiesSet() {
        UploadFactory.register(UploadType.QCLOUD.getType(), this);
    }
}
