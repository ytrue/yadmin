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


    public void cancelOrder() {
         log.info("我是OrderTask--cancelOrder：取消订单---我被触发了");
    }

    /**
     * 确认收货
     */
    public void confirmOrder() {
         log.info("我是OrderTask--confirmOrder：确认收货---我被触发了");
    }


    /**
     * 测试方法
     */
    public void test() {
         log.info("我是OrderTask--test：测试方法---我被触发了");
    }

}
