package com.ytrue.yadmin.model.chat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天联系人表
 */
@Data
@TableName("chat_contact")
public class ChatContact implements Serializable {
    private static final long serialVersionUID = 5660015004587617164L;
    /**
     * 联系人ID
     */
    @TableId
    @TableField("contact_id")
    private Integer contactId;


    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

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
     * 是否是群:0=否,1=是
     */
    @TableField("is_group")
    private Integer isGroup;
    /**
     * 是否删除:0=否,1=是
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
