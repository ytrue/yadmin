package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
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

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库分组记录表
 */
@Data
@TableName("sys_attachment_group")
@ApiModel(value = "文件库分组记录")
public class SysAttachmentGroup implements Serializable {
    private static final long serialVersionUID = 6914215265817006956L;
    /**
     * 分组ID
     */
    @TableId
    @TableField("group_id")
    @ApiModelProperty(value = "主键")
    private Integer groupId;
    /**
     * 分组名称
     */
    @NotBlank(message = "名称不能为空")
    @TableField("name")
    @ApiModelProperty(value = "分组名称",required = true)
    private String name;
    /**
     * 上级分组ID
     */
    @TableField("parent_id")
    @ApiModelProperty(value = "上级分组ID", required = true)
    private Integer parentId;
    /**
     * 排序(数字越小越靠前)
     */
    @TableField("sort")
    @ApiModelProperty(value = "排序(数字越小越靠前)", required = true)
    private Integer sort;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     *
     */
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
