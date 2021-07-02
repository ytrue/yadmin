package com.ytrue.yadmin.oss.parameter.cloud;

import cn.hutool.core.convert.Convert;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.oss.parameter.enums.UploadType;
import com.ytrue.yadmin.oss.parameter.factory.UploadFactory;
import com.ytrue.yadmin.oss.parameter.properties.AliyunProperties;
import com.ytrue.yadmin.oss.parameter.properties.BaseProperties;
import com.ytrue.yadmin.oss.parameter.properties.LocalProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/7/2 15:47
 * @description LocalUpload
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class LocalUpload extends AbstractUpload {

    private LocalProperties localProperties;

    @Override
    public String upload(byte[] data, String fileName) {
        return upload(localProperties, data, fileName);
    }


    @Override
    public String upload(BaseProperties properties, byte[] data, String fileName) {
        LocalProperties properties1 = Convert.convert(LocalProperties.class, properties);
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filePath = getUploadPath(sdf.format(new Date()), properties1.getFileHost());
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(filePath + File.separator + fileName)
                    )
            );
            out.write(data);
            out.flush();
            return properties1.getDomain() + "/" +
                    properties1.getFileHost() + "/" +
                    sdf.format(new Date()) +
                    "/" +
                    fileName;

        } catch (IOException e) {
            throw new YadminException("上传文件失败" + e.getMessage());
        }
    }


    /**
     * 获取当前系统路径
     */
    private String getUploadPath(String day, String fileHost) {
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
        File upload = new File(path.getAbsolutePath(), "static/" + fileHost + "/" + day);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }

    @Override
    public void afterPropertiesSet() {
        UploadFactory.register(UploadType.LOCAL.getType(), this);
    }
}
