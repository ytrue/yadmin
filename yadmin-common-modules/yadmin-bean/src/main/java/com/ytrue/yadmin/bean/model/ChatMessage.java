package com.ytrue.yadmin.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * int如果设为空的话默认为0而Integer为Null
 *
 * @author ytrue
 * @date 2021/4/2 10:15
 * @description 消息表
 */
@Data
@TableName("chat_message")
public class ChatMessage {

    /**
     * 消息id
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Integer messageId;

    /**
     * 消息发送的状态：going | failed | succeed
     */
    @TableField("status")
    private String status;

    /**
     * 消息类型：file | image | text | event
     */
    @TableField("type")
    private String type;

    /**
     * 消息发送时间，13位毫秒
     */
    @TableField("send_time")
    private Long sendTime;

    /**
     * 消息内容，如果type=file，此属性表示文件的URL地址
     */
    @TableField("content")
    private String content;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Double fileSize;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 接收消息的联系人ID
     */
    @TableField("to_contact_id")
    private Integer toContactId;

    /**
     * 消息发送人的信息ID
     */
    @TableField("from_user_id")
    private Integer fromUserId;
}
