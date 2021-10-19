package com.ytrue.yadmin.controller;


import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.security.util.JwtUtil;
import com.ytrue.yadmin.service.ChatContactService;
import com.ytrue.yadmin.util.RequestUtil;
import com.ytrue.yadmin.vo.ContactVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    private final JwtUtil jwtUtil;

    private final HttpServletRequest request;

    /**
     * 获得当前用户的联系人
     *
     * @return
     */
    @GetMapping("list")
    public List<ContactVO> getMyContact() {
        return chatContactService.getMyContactById(jwtUtil.getUserIdFromToken(RequestUtil.getToken(request)));
    }


    /**
     * 获取我的信息
     *
     * @return
     */
    @GetMapping("my")
    public ChatContact my() {
        return chatContactService.getById(jwtUtil.getUserIdFromToken(RequestUtil.getToken(request)));
    }

}
