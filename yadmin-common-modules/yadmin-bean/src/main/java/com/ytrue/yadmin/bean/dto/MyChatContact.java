package com.ytrue.yadmin.bean.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/6 15:17
 */
@Data
public class MyChatContact {

    /**
     * 唯一ID
     */
    private Integer id;

    /**
     * 未读消息数
     */
    private Integer unread;

    /**
     * 最近一条消息的时间戳，13位毫秒
     */
    private Long lastSendTime;

    /**
     * 最近一条消息的内容
     */
    private String lastContent;

    /**
     * 名称
     */
    private String displayName;

    /**
     * 通讯录索引，传入字母或数字进行排序，索引可以显示自定义文字“[1]群组”
     */
    private String index;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 0=一对一,1=一对多
     */
    private Integer isGroup;
}
