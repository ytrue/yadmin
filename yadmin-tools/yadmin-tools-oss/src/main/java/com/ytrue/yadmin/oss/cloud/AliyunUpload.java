package com.ytrue.yadmin.oss.cloud;

import cn.hutool.core.convert.Convert;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.oss.dto.Engine;
import com.ytrue.yadmin.oss.enums.UploadType;
import com.ytrue.yadmin.oss.factory.UploadFactory;
import com.ytrue.yadmin.oss.properties.AliyunProperties;
import com.ytrue.yadmin.oss.properties.BaseProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 阿里云上传
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AliyunUpload extends AbstractUpload {

    private AliyunProperties aliyunProperties;

    @Override
    public String upload(Engine engine, byte[] data, String fileName) {
        return upload(engine.getAliyun(), data, fileName);
    }

    @Override
    public String upload(byte[] data, String fileName) {
        return upload(aliyunProperties, data, fileName);
    }


    @Override
    public String upload(BaseProperties properties, byte[] data, String fileName) {
        try {
            AliyunProperties properties1 = Convert.convert(AliyunProperties.class, properties);

            // 存储目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dir = properties1.getFileHost() + "/" + sdf.format(new Date());
            String path = dir + "/" + fileName;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(
                    properties1.getDomain(),
                    properties1.getAccessKeyId(),
                    properties1.getAccessKeySecret()
            );
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(properties1.getBucket(),
                            path, new ByteArrayInputStream(data));

            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            // 关闭OSSClient。
            ossClient.shutdown();
            //去除http和https前缀
            URL url = new URL(properties1.getDomain());
            return  "123";

        } catch (Exception e) {
            throw new YadminException("阿里云上传文件失败" + e.getMessage());
        }
    }

    /**
     * 注册
     */
    @Override
    public void afterPropertiesSet() {
        UploadFactory.register(UploadType.ALIYUN.getType(), this);
    }
}
