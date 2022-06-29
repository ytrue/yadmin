package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 部门
 */
@Data
@Builder
@ApiModel(value = "部门")
@TableName("sys_dept")
@Accessors(chain = true)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @TableField("dept_id")
    @ApiModelProperty(value = "ID")
    private Long deptId;

    @TableField("pid")
    @ApiModelProperty(value = "上级部门")
    private Long pid;

    @TableField("sub_count")
    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;

    @TableField("dept_sort")
    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    @TableField("enabled")
    @ApiModelProperty(value = "状态")
    private boolean enabled;

    @TableField("create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField("update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @TableField("create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
