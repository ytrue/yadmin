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
import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 岗位
 */
@Data
@TableName("sys_job")
@Builder
@ApiModel(value = "岗位")
@Accessors(chain = true)
public class SysJob implements Serializable {
    /**
     * ID
     */
    @TableId
    @TableField("job_id")
    @ApiModelProperty(value = "ID")
    private Long jobId;

    @TableField("name")
    @ApiModelProperty(value = "岗位名称")
    private String name;

    @TableField("enabled")
    @ApiModelProperty(value = "岗位状态")
    private boolean enabled;


    @TableField("job_sort")
    @ApiModelProperty(value = "排序")
    private Integer jobSort;

    @TableField("create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField("update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @TableField("create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
