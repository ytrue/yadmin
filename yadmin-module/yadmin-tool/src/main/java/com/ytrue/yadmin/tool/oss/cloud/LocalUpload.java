package com.ytrue.yadmin.tool.oss.cloud;

import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.tool.oss.enums.UploadType;
import com.ytrue.yadmin.tool.oss.dto.UploadSetting;
import com.ytrue.yadmin.tool.oss.factory.StrategyUploadFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 本地上传
 * 在本地开发测试模式时，得到的地址为： {项目跟目录}/target/static/images/upload/
 * 在打包成jar发布到服务器上时，得到的地址为： {发布jar包目录}/static/images/upload/
 *
 */
@Component
public class LocalUpload extends AbstractStrategyUpload {


    @Override
    public String upload(UploadSetting uploadSetting, byte[] data, String fileName) {
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String filepath = getUploadPath(sdf.format(new Date()));
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(filepath + File.separator + fileName)
                    )
            );
            out.write(data);
            out.flush();
            return
                    "/upload"
                            + "/" + sdf.format(new Date())
                            + "/" + fileName;

        } catch (IOException e) {
            throw new YadminException("上传文件失败" + e.getMessage());
        }
    }


    /**
     * 注册
     */
    @Override
    public void afterPropertiesSet() {
        StrategyUploadFactory.register(UploadType.LOCAL.getType(), this);
    }


    /**
     * 获取当前系统路径
     */
    private String getUploadPath(String day) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert path != null;
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static/upload/" + day);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }
}
