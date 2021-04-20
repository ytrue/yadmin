package com.ytrue.yadmin.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/2 10:15
 * @description 联系人
 */
@Data
@TableName("chat_contact")
public class ChatContact {

    /**
     * 联系人id
     */
    @TableId(type = IdType.AUTO)
    @TableField("contact_id")
    private Integer contactId;

    /**
     * 名称
     */
    @TableField("display_name")
    private String displayName;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 0=一对一,1=一对多
     */
    @TableField("is_group")
    private Integer isGroup;
}
