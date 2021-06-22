package com.ytrue.yadmin.system.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.common.utils.FileUtils;
import com.ytrue.yadmin.common.utils.GsonUtils;
import com.ytrue.yadmin.system.dao.UploadFileDAO;
import com.ytrue.yadmin.system.dao.UploadGroupDAO;
import com.ytrue.yadmin.system.model.UploadFile;
import com.ytrue.yadmin.system.service.UploadFileService;
import com.ytrue.yadmin.system.service.dto.MoveGroupParamDTO;
import com.ytrue.yadmin.tools.module.oss.cloud.AbstractStrategyUpload;
import com.ytrue.yadmin.tools.module.oss.dto.UploadSetting;
import com.ytrue.yadmin.tools.module.oss.factory.StrategyUploadFactory;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UploadFileServiceImpl extends ServiceImpl<UploadFileDAO, UploadFile> implements UploadFileService {


    private final UploadGroupDAO uploadGroupDAO;

    @SneakyThrows
    @Override
    public void uploadFile(MultipartFile file) {
        //先模拟一个json数据
        String data = "{\n" +
                "  \"defaultEngine\": \"local\",\n" +
                "  \"engine\": {\n" +
                "    \"local\": {\n" +
                "      \"domain\": \"http://127.0.0.1:9995\"\n" +
                "    },\n" +
                "    \"qiniu\": {\n" +
                "      \"bucket\": \"php-java-ytrue\",\n" +
                "      \"accessKey\": \"v9vizwHCQrvxrySUNi36vd4MKn5s24A2JCr6837C\",\n" +
                "      \"secretKey\": \"tigkSWysBE5d6lH0k6B1TkO4x-C2cRWJJ0zIERL2\",\n" +
                "      \"domain\": \"http://qlwboyapr.hn-bkt.clouddn.com\"\n" +
                "    },\n" +
                "    \"aliyun\": {\n" +
                "      \"bucket\": \"php-yangyi-images\",\n" +
                "      \"accessKeyId\": \"LTAI4G8W2u1s2hXaNTRzu7jP\",\n" +
                "      \"accessKeySecret\": \"uVs78nQ4wgdjH0fToD8aOGFyOMHbzB\",\n" +
                "      \"domain\": \"https://oss-cn-shenzhen.aliyuncs.com\"\n" +
                "    },\n" +
                "    \"qcloud\": {\n" +
                "      \"bucket\": \"ytrue-php-java-1304614471\",\n" +
                "      \"region\": \"ap-nanjing\",\n" +
                "      \"secretId\": \"AKIDIBwSq5Q9VgnkljrCDbq06OVNkBfuEWGk\",\n" +
                "      \"secretKey\": \"pPbdcKpyiFerUu8LMkuMjtVugLncAzeS\",\n" +
                "      \"domain\": \"https://ytrue-php-java-1304614471.cos.ap-nanjing.myqcloud.com\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        //获得设置
        UploadSetting uploadSetting = GsonUtils.from(data, UploadSetting.class);
        //重命名文件名
        String extName = FileUtil.extName(file.getOriginalFilename());
        assert extName != null;
        String fileName = UUID.randomUUID() + "." + extName;
        //调用业务
        String engine = uploadSetting.getDefaultEngine();
        AbstractStrategyUpload strategyUpload = StrategyUploadFactory.getInvokeStrategy(engine);
        //返回的url路径
        String path = strategyUpload.upload(uploadSetting, file.getBytes(), fileName);
        //插入数据
        UploadFile uploadFile = new UploadFile();
        //获得文件的名称
        uploadFile.setFileName(file.getOriginalFilename());
        //文件的路径
        uploadFile.setFilePath(path);
        //获得文件大小
        uploadFile.setFileSize(FileUtils.getPrintSize(file.getSize()));
        //后缀名称
        uploadFile.setFileExt(extName);
        //插入数据
        save(uploadFile);
    }

    @Override
    public void moveGroup(MoveGroupParamDTO paramDTO) {
        //获得组
        Integer groupId = paramDTO.getGroupId();
        //这是修改内容
        UploadFile uploadFile = new UploadFile();
        uploadFile.setGroupId(groupId);
        //循环修改
        paramDTO.getFileIds().forEach(fileId -> {
            update(uploadFile, new QueryWrapper<UploadFile>().eq("file_id", fileId));
        });
    }
}
