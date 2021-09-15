package com.ytrue.yadmin.modules.system.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
@Data
@TableName("sys_role")
@ApiModel(value = "角色")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 6432050663873058679L;
    /**
     * 角色ID
     */
    @TableId
    @ApiModelProperty(value = "主键")
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称",required = true)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<Long> menuIdList;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
