package com.ytrue.yadmin.bean.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/2 10:15
 * @description 消息未读信息
 */
@Data
@TableName("chat_relation")
public class ChatRelation {

    /**
     * ID
     */
    @TableId
    @TableField("relation_id")
    private Integer relationId;

    /**
     * 接收消息的联系人ID
     */
    @TableField("to_contact_id")
    private Integer toContactId;

    /**
     * 消息发送人的信息ID
     */
    @TableField("form_user_id")
    private Integer formUserId;

    /**
     * 未读消息数量
     */
    @TableField("unread")
    private Integer unread;

    /**
     * 最近一条消息的时间戳，13位毫秒
     */
    @TableField("last_send_time")
    private Integer lastSendTime;

    /**
     * 最近一条消息的内容
     */
    @TableField("last_content")
    private String lastContent;

    /**
     * 名称备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 索引
     */
    @TableField("index")
    private String index;

    /**
     * 好友的头像
     */
    @TableField("to_contact_avatar")
    private String toContactAvatar;

    /**
     * 版本号
     */
   // @Version
    private Integer version;

}
