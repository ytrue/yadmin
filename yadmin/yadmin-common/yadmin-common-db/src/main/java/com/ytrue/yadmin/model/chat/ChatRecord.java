package com.ytrue.yadmin.model.chat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天记录
 */
@Data
@TableName("chat_record")
public class ChatRecord implements Serializable {
    private static final long serialVersionUID = 5882317689856514981L;
    /**
     * ID
     */
    @TableId
    @TableField("chat_record_id")
    private Long chatRecordId;
    /**
     * 消息发送的状态:发送中=going,失败:failed,成功:succeed
     */
    @TableField("status")
    private String status;
    /**
     * 消息类型:文件=file,图片=image,文本=text,事件event
     */
    @TableField("type")
    private String type;
    /**
     * 客户端消息发送时间，13位毫秒
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
    private Long toContactId;
    /**
     * 消息发送人的信息ID
     */
    @TableField("from_contact_id")
    private Integer fromContactId;
    /**
     * 到达时间，13位毫秒
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 客户端生成的唯一ID
     */
    @TableField("id")
    private String id;
}
