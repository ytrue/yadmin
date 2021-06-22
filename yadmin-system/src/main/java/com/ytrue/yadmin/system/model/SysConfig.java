package com.ytrue.yadmin.system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统配置信息
 */
@Data
@TableName("sys_config")
public class SysConfig {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 参数名
     */
    @NotBlank(message = "参数名不能为空")
    private String paramKey;

    /**
     * 参数值
     */
    @NotBlank(message = "参数值不能为空")
    private String paramValue;

    /**
     * 备注
     */
    private String remark;

}
