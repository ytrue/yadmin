package com.ytrue.yadmin.tools.oss.cloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.tools.oss.enums.UploadType;
import com.ytrue.yadmin.tools.oss.dto.UploadSetting;
import com.ytrue.yadmin.tools.oss.factory.StrategyUploadFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 阿里云上传
 */
@Component
public class AliyunUpload extends AbstractStrategyUpload {


    @Override
    public String upload(UploadSetting uploadSetting, byte[] data, String fileName) {
        try {
            // 存储目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dir = "mall/images/" + sdf.format(new Date());
            String path = dir + "/" + fileName;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(
                    uploadSetting.getEngine().getAliyun().getDomain(),
                    uploadSetting.getEngine().getAliyun().getAccessKeyId(),
                    uploadSetting.getEngine().getAliyun().getAccessKeySecret()
            );

            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(uploadSetting.getEngine().getAliyun().getBucket(),
                            path, new ByteArrayInputStream(data));

            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            // 关闭OSSClient。
            ossClient.shutdown();
            //去除http和https前缀
            URL url = new URL(uploadSetting.getEngine().getAliyun().getDomain());
            return url.getProtocol() + "://" + uploadSetting.getEngine().getAliyun().getBucket() +
                    "." +
                    url.getHost() +
                    "/" +
                    path;
        } catch (Exception e) {
            throw new YadminException("上传文件失败，请核对阿里云配置信息" + e.getMessage());
        }
    }

    /**
     * 注册
     *
     */
    @Override
    public void afterPropertiesSet() {
        StrategyUploadFactory.register(UploadType.ALIYUN.getType(), this);
    }
}
