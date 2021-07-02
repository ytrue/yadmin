package com.ytrue.yadmin.config;

import com.ytrue.yadmin.log.entity.OptLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2021/7/1 17:47
 * @description LogService
 */
@Service
@Slf4j
public class LogService {

    /**
     * 将日志信息保存到数据库
     *
     * @param optLogDTO
     */
    public void saveLog(OptLogDTO optLogDTO) {
        //此处只是将日志信息进行输出，实际项目中可以将日志信息保存到数据库
        log.info("保存日志信息：" + optLogDTO);
    }
}
