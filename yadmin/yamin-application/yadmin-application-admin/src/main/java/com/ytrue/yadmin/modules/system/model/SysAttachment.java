package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */

@Data
@TableName("sys_attachment")
@ApiModel(value = "文件库记录")
public class SysAttachment implements Serializable {
    private static final long serialVersionUID = -1977241509435137545L;
    /**
     * 文件ID
     */
    @TableId
    @TableField("file_id")
    @ApiModelProperty(value = "主键")
    private Integer fileId;
    /**
     * 文件分组ID
     */
    @TableField("group_id")
    @ApiModelProperty(value = "文件分组ID", required = true)
    private Integer groupId;

    /**
     * 文件类型(10图片 20附件 30视频)
     */
    @TableField("file_type")
    @ApiModelProperty(value = "文件类型(10图片 20附件 30视频)", required = true)
    private Integer fileType;
    /**
     * 文件名称(仅显示)
     */
    @ApiModelProperty(value = "文件名称(仅显示)", required = true)
    @TableField("file_name")
    private String fileName;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径", required = true)
    @TableField("file_path")
    private String filePath;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小", required = true)
    @TableField("file_size")
    private String fileSize;

    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名", required = true)
    @TableField("file_ext")
    private String fileExt;

    /**
     * 创建时间,这里使用自动填充
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间,这里使用自动填充
     */
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
