package com.ytrue.yadmin.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/10/14 15:22
 * @description 接收和发送消息的模板
 */
@Data
@Builder
public class Message implements Serializable {
    private static final long serialVersionUID = 668621975948168831L;

    /**
     * 消息id
     */
    private String id;

    /**
     * 状态
     */
    private String status;

    /**
     * ；类型
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
