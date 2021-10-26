package com.ytrue.yadmin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/10/14 15:22
 * @description 接收和发送消息的模板
 */
@Data
public class  MessageDTO implements Serializable {
    private static final long serialVersionUID = 668621975948168831L;

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息发送的状态：going | failed | succeed
     */
    private String status;

    /**
     * 消息类型：file | image | text | event
     */
    private String type;

    /**
     * 发送类型
     */
    private String sendType;

    /**
     * 发送时间
     */
    private Long sendTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 接收者id
     */
    private Long toContactId;

    /**
     * 消息发送人的信息
     */
    private FromUser fromUser;

}
