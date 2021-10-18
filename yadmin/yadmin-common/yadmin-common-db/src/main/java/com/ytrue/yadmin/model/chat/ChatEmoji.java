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
 * @description 聊天表情库
 */
@Data
@TableName("chat_emoji")
public class ChatEmoji implements Serializable {
    private static final long serialVersionUID = 122079492177165780L;
    /**
     * ID
     */
    @TableId
    @TableField("emoji_id")
    private Long emojiId;
    /**
     * 所属分类ID
     */
    @TableField("emoji_category_id")
    private Long emojiCategoryId;
    /**
     * 表情名称
     */
    @TableField("name")
    private String name;
    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 图片地址
     */
    @TableField("src")
    private String src;
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
