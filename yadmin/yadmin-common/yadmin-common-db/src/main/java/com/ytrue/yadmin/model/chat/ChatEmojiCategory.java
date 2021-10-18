package com.ytrue.yadmin.model.chat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 表情分类
 */
@Data
@TableName("chat_emoji_category")
public class ChatEmojiCategory implements Serializable {
    private static final long serialVersionUID = -4185483487771060828L;
    /**
     * ID
     */
    @TableId
    @TableField("emoji_category_id")
    private Long emojiCategoryId;
    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;
    /**
     * 是否删除:0=否,1=是
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
