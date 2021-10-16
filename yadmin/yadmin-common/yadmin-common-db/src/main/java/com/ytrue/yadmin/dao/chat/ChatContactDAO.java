package com.ytrue.yadmin.dao.chat;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.vo.ContactVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天联系人表
 */
public interface ChatContactDAO extends BaseMapper<ChatContact> {

    /**
     * 根据id获得我的联系人
     * <p>
     * <p>
     * SELECT
     * c.contact_id AS id,
     * c.avatar,
     * r.remark AS display_name,
     * r.`index`,
     * r.last_send_time,
     * r.last_content,
     * r.unread,
     * c.is_group
     * FROM
     * chat_contact AS c
     * LEFT JOIN chat_contact_relation AS r ON c.contact_id = r.from_contact_id
     * WHERE
     * r.from_contact_id = 2
     *
     * @param contactId 联系人id
     * @return
     */
    @Select("SELECT\n" +
            "c.contact_id AS id,\n" +
            "c.avatar,\n" +
            "r.remark AS display_name,\n" +
            "r.`index`,\n" +
            "r.last_send_time,\n" +
            "r.last_content,\n" +
            "r.unread,\n" +
            "c.is_group\n" +
            "FROM\n" +
            "chat_contact AS c\n" +
            "LEFT JOIN chat_contact_relation AS r ON c.contact_id = r.from_contact_id\n" +
            "WHERE\n" +
            "r.from_contact_id = #{contactId}")
    List<ContactVO> getMyContactById(@Param("contactId") Integer contactId);

    /**
     * 获得当前用户侧栏聊天消息列表，限制是前150条
     *
     * SELECT
     * c.contact_id AS id,
     * c.avatar,
     * r.remark AS display_name,
     * r.`index`,
     * r.last_send_time,
     * r.last_content,
     * r.unread,
     * c.is_group
     * FROM
     * chat_contact AS c
     * LEFT JOIN chat_contact_relation AS r ON c.contact_id = r.from_contact_id
     * WHERE
     * r.from_contact_id = 2 AND
     * r.is_show = 1
     * LIMIT 0, 150
     *
     * @param contactId
     * @return
     */
    @Select("SELECT\n" +
            "c.contact_id AS id,\n" +
            "c.avatar,\n" +
            "r.remark AS display_name,\n" +
            "r.`index`,\n" +
            "r.last_send_time,\n" +
            "r.last_content,\n" +
            "r.unread,\n" +
            "c.is_group\n" +
            "FROM\n" +
            "chat_contact AS c\n" +
            "LEFT JOIN chat_contact_relation AS r ON c.contact_id = r.from_contact_id\n" +
            "WHERE\n" +
            "r.from_contact_id =  #{contactId} AND\n" +
            "r.is_show = 1\n" +
            "LIMIT 0, 150\n")
    List<ContactVO> getMySidebarMessageById(@Param("contactId") Integer contactId);
}
