package com.ytrue.yadmin.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description OrderTask
 */
@Slf4j
@Component("orderTask")
public class OrderTask {


    /**
     * 取消订单,这里以后会使用延迟队列
     */
    public void cancelOrder() {
         log.info("我是OrderTask--cancelOrder：取消订单---我被触发了");
    }

}
