package com.ytrue.yadmin.controller;

import com.ytrue.yadmin.service.ChatRecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天记录
 */
@RestController
@RequestMapping("chat/record")
@AllArgsConstructor
public class ChatRecordController {

    private final ChatRecordService chatRecordService;


    @GetMapping("page")
    public Object page() {
        return chatRecordService.getChatRecordList(2L, 1L, 0L, 10L);
    }
}
