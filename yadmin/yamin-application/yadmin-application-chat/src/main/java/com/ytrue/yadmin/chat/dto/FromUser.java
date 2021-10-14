package com.ytrue.yadmin.chat.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/4/6 16:52
 * 消息发送人的信息
 */
@Data
public class FromUser implements Serializable {

    private static final long serialVersionUID = -757418959740079485L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String displayName;

    /**
     * 头像
     */
    private String avatar;
}
