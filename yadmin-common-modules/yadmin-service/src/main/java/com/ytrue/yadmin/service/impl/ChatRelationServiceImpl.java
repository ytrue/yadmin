package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.bean.model.ChatRelation;
import com.ytrue.yadmin.dao.ChatRelationMapper;
import com.ytrue.yadmin.service.ChatRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ytrue
 * @date 2021/4/2 11:03
 */
@Service
@Slf4j
public class ChatRelationServiceImpl extends ServiceImpl<ChatRelationMapper, ChatRelation> implements ChatRelationService {

 //   @Autowired
   // private StringRedisTemplate stringRedisTemplate;

//    @Override
//    @Async("chatMessageSaveExecutor")
//    public void test(String string, Long integer) {

//        try {
//            stringRedisTemplate.opsForHash().increment("test-hash", string, integer);
//            System.out.println("我是线程"+Thread.currentThread().getName());
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
  //  }
}
