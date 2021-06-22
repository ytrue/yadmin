package com.ytrue.yadmin.system.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */

@Data
@TableName("upload_file")
public class UploadFile {
    /**
     * 文件ID
     */
    @TableId
    @TableField("file_id")
    private Integer fileId;
    /**
     * 文件分组ID
     */
    @TableField("group_id")
    private Integer groupId;

    /**
     * 文件类型(10图片 20附件 30视频)
     */
    @TableField("file_type")
    private Integer fileType;
    /**
     * 文件名称(仅显示)
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 文件大小
     */
    @TableField("file_size")
    private String fileSize;
    /**
     * 文件扩展名
     */
    @TableField("file_ext")
    private String fileExt;

    /**
     * 创建时间,这里使用自动填充
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间,这里使用自动填充
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
