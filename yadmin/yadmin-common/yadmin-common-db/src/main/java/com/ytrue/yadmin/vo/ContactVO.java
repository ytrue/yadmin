package com.ytrue.yadmin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/10/15 23:22
 * @description 侧栏聊天列表和联系人列表返回
 */
@Data
public class ContactVO implements Serializable {
    private static final long serialVersionUID = 6750520547703975511L;
    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String displayName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否是群:0=否,1=是
     */
    private Boolean isGroup;

    /**
     * 未读消息数
     */
    private Integer unread;


    /**
     *
     */
    private String index;

    /**
     * 最近一条消息的时间戳,13位毫秒
     */
    private Long lastSendTime;

    /**
     * 最近一条消息的内容
     */
    private String lastContent;
}
