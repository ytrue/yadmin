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
 * @description 联系人关系表
 */
@Data
@TableName("chat_contact_relation")
public class ChatContactRelation implements Serializable {
    private static final long serialVersionUID = 6570356749327325858L;
    /**
     * ID
     */
    @TableId
    @TableField("contact_relation_id")
    private Long contactRelationId;
    /**
     * 接收消息的联系人ID
     */
    @TableField("to_contact_id")
    private Long toContactId;
    /**
     * 消息发送人的信息ID
     */
    @TableField("from_contact_id")
    private Long fromContactId;
    /**
     * 未读消息数
     */
    @TableField("unread")
    private Integer unread;
    /**
     * 最近一条消息的时间戳,13位毫秒
     */
    @TableField("last_send_time")
    private Long lastSendTime;
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
     * 通讯录索引，传入字母或数字进行排序，索引可以显示自定义文字“[1]群组”
     */
    @TableField("index")
    private String index;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 关系时间
     */
    @TableField("update_time")
    private Date updateTime;
}
