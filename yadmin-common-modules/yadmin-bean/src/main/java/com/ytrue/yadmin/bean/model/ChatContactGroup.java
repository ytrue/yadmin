package com.ytrue.yadmin.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/9 13:30
 */
@Data
@TableName("chat_contact_group")
public class ChatContactGroup {

    /**
     * id主键
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Integer id;

    /**
     *群id
     */
    @TableField("group_id")
    private Integer groupId;

    /**
     *用户id
     */
    @TableField("contact_id")
    private Integer contactId;
}
