package com.ytrue.yadmin.tools.module.oss.cloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.tools.module.oss.enums.UploadType;
import com.ytrue.yadmin.tools.module.oss.dto.UploadSetting;
import com.ytrue.yadmin.tools.module.oss.factory.StrategyUploadFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 腾讯云
 */
@Component
public class QCloudUpload extends AbstractStrategyUpload {

    @Override
    public String upload(UploadSetting uploadSetting, byte[] data, String fileName) {
        try {
            // 存储目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dir = "mall/images/" + sdf.format(new Date());
            String path = dir + "/" + fileName;

            //初始化用户身份信息
            COSCredentials cosCredentials = new BasicCOSCredentials(
                    uploadSetting.getEngine().getQcloud().getSecretId(),
                    uploadSetting.getEngine().getQcloud().getSecretKey()
            );
            //设置bucket区域，
            //clientConfig中包含了设置region
            ClientConfig clientConfig = new ClientConfig(
                    new Region(uploadSetting.getEngine().getQcloud().getRegion())
            );
            //生成cos客户端
            COSClient cosClient = new COSClient(cosCredentials, clientConfig);
            // 指定要上传到 COS 上对象键
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    uploadSetting.getEngine().getQcloud().getBucket(),
                    path,
                    new ByteArrayInputStream(data),
                    null
            );
            cosClient.putObject(putObjectRequest);
            return uploadSetting.getEngine().getQcloud().getDomain() +
                    "/" +
                    path;
        } catch (Exception e) {
            throw new YadminException("上传文件失败，请核对腾讯云配置信息" + e.getMessage());
        }

    }

    /**
     * 注册
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyUploadFactory.register(UploadType.QCLOUD.getType(), this);
    }
}
