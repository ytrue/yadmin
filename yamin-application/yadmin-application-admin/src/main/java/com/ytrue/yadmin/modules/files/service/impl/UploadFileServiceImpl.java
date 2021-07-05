package com.ytrue.yadmin.modules.files.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.files.UploadFileDAO;
import com.ytrue.yadmin.dao.files.UploadGroupDAO;
import com.ytrue.yadmin.model.files.UploadFile;
import com.ytrue.yadmin.modules.files.service.UploadFileService;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

//        //获得设置
//        UploadSetting uploadSetting = GsonUtils.from(data, UploadSetting.class);
//        //重命名文件名
//        String extName = FileUtil.extName(file.getOriginalFilename());
//        assert extName != null;
//        String fileName = UUID.randomUUID() + "." + extName;
//        //调用业务
//        String engine = uploadSetting.getDefaultEngine();
//        AbstractStrategyUpload strategyUpload = StrategyUploadFactory.getInvokeStrategy(engine);
//        //返回的url路径
//        String path = strategyUpload.upload(uploadSetting, file.getBytes(), fileName);
//        //插入数据
//        UploadFile uploadFile = new UploadFile();
//        //获得文件的名称
//        uploadFile.setFileName(file.getOriginalFilename());
//        //文件的路径
//        uploadFile.setFilePath(path);
//        //获得文件大小
//        uploadFile.setFileSize(FileUtils.getPrintSize(file.getSize()));
//        //后缀名称
//        uploadFile.setFileExt(extName);
//        //插入数据
//        save(uploadFile);
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
