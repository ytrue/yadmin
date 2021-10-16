package com.ytrue.yadmin.controller;

import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.service.ChatContactService;
import com.ytrue.yadmin.vo.ContactVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/15 23:46
 * @description 联系人关系表
 */
@RestController
@RequestMapping("chat/contact")
@AllArgsConstructor
public class ChatContactController {


    private final ChatContactService chatContactService;

    /**
     * 获得当前用户侧栏聊天消息列表，限制是前150条
     *
     * @param contactId
     * @return
     */
    @GetMapping("message")
    public List<ContactVO> getMySidebarMessage(@RequestParam("contactId") Integer contactId) {
        return chatContactService.getMySidebarMessageById(contactId);
    }

    /**
     * 获得当前用户的联系人
     *
     * @param contactId
     * @return
     */
    @GetMapping("list")
    public List<ContactVO> getMyContact(@RequestParam("contactId") Integer contactId) {
        return chatContactService.getMyContactById(contactId);
    }


    /**
     * 获取我的信息
     *
     * @param contactId
     * @return
     */
    @GetMapping("my")
    public ChatContact my(@RequestParam("contactId") Integer contactId) {
        return chatContactService.getById(contactId);
    }

}
