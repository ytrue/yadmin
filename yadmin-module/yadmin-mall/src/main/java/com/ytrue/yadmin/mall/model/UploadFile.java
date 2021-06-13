package com.ytrue.yadmin.mall.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


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
     * 上传来源(10商户后台 20用户端)
     */
    @TableField("channel")
    private Integer channel;
    /**
     * 存储方式
     */
    @TableField("storage")
    private String storage;
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
     * 文件大小(字节)
     */
    @TableField("file_size")
    private Integer fileSize;
    /**
     * 文件扩展名
     */
    @TableField("file_ext")
    private String fileExt;
    /**
     * 文件封面
     */
    @TableField("cover")
    private String cover;
    /**
     * 是否在回收站
     */
    @TableField("is_recycle")
    private Integer isRecycle;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
}
