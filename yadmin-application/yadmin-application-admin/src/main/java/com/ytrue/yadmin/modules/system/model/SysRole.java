package com.ytrue.yadmin.modules.system.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "角色")
@TableName("sys_role")
public class SysRole implements Serializable {


    private static final long serialVersionUID = -6350234728443853054L;
    @TableId
    @TableField("role_id")
    @ApiModelProperty(value = "id")
    private Long roleId;


    @TableField("role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
