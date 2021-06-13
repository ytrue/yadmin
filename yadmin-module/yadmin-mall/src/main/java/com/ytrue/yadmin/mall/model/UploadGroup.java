package com.ytrue.yadmin.mall.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
     * 是否删除
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Integer createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Integer updateTime;
}
