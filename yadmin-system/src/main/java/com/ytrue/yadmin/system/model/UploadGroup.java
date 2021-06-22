package com.ytrue.yadmin.system.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库分组记录表
 */
@Data
@TableName("upload_group")
public class UploadGroup {
    /**
     * 分组ID
     */
    @TableId
    @TableField("group_id")
    private Integer groupId;
    /**
     * 分组名称
     */
    @NotBlank(message = "名称不能为空")
    @TableField("name")
    private String name;
    /**
     * 上级分组ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 排序(数字越小越靠前)
     */
    @TableField("sort")
    private Integer sort;


    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
