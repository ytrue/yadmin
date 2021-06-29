package com.ytrue.yadmin.tools.oss.cloud;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.tools.oss.enums.UploadType;
import com.ytrue.yadmin.tools.oss.dto.UploadSetting;
import com.ytrue.yadmin.tools.oss.factory.StrategyUploadFactory;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 七牛云
 */
@Component
public class QiniuUpload extends AbstractStrategyUpload {

    @SneakyThrows
    @Override
    public String upload(UploadSetting uploadSetting, byte[] data, String fileName) {
        try {
            Configuration cfg = new Configuration(Region.huanan());
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(
                    uploadSetting.getEngine().getQiniu().getAccessKey(),
                    uploadSetting.getEngine().getQiniu().getSecretKey()
            );
            String upToken = auth.uploadToken(uploadSetting.getEngine().getQiniu().getBucket());
            Response response = uploadManager.put(data, fileName, upToken);
            if (!response.isOK()) {
                throw new RuntimeException("上传七牛出错：" + response.toString());
            }

            return uploadSetting.getEngine().getQiniu().getDomain() + "/" + fileName;
        } catch (Exception e) {
            throw new YadminException("上传文件失败，请核对七牛配置信息" + e.getMessage());
        }
    }

    /**
     * 注册
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyUploadFactory.register(UploadType.QINIU.getType(), this);
    }
}
