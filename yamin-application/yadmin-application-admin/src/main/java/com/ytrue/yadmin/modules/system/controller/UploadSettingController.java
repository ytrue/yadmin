package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.dao.SettingDao;
import com.ytrue.yadmin.model.Setting;
import com.ytrue.yadmin.oss.dto.UploadSetting;
import com.ytrue.yadmin.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ytrue
 * @date 2021/8/3 15:32
 * @description UploadSettingController
 */
@RestController
@RequestMapping("files/setting")
@Api(tags = "上传设置")
public class UploadSettingController {

    @Autowired
    private SettingDao settingDao;

    private final static String STORAGE = "storage";

    @GetMapping
    @ApiOperation(value = "获得上传设置")
    public UploadSetting info() {
        return GsonUtils.from(settingDao.selectOne(
                new QueryWrapper<Setting>()
                        .lambda()
                        .eq(Setting::getKey, STORAGE)).getValues(), UploadSetting.class);
    }

    @PutMapping
    @ApiOperation(value = "保存上传设置")
    public void save(@RequestBody UploadSetting uploadSetting) {
        String str = GsonUtils.to(uploadSetting);
        Setting setting = new Setting();
        setting.setValues(str);
        settingDao.update(setting, new QueryWrapper<Setting>().lambda().eq(Setting::getKey, STORAGE));
    }

}
