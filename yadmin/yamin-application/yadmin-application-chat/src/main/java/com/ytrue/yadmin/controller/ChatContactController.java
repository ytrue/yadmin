package com.ytrue.yadmin.controller;

import com.ytrue.yadmin.service.ChatContactService;
import com.ytrue.yadmin.vo.ContactVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/15 23:46
 * @description 联系人关系表
 */
@RestController
@RequestMapping("contact")
@AllArgsConstructor
public class ChatContactController {


    private final ChatContactService chatContactService;

    @GetMapping("test")
    public List<ContactVO> getMyContactById(@RequestParam("contactId") Integer contactId) {


        return chatContactService.getMyContactById(contactId);
    }
}
