package com.ytrue.yadmin.tools.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description Local 引擎聚合
 */
@Data
public class Engine {

    /**
     * 阿里配置
     */
    private Aliyun aliyun;

    /**
     * 七牛云配置
     */
    private Qiniu qiniu;

    /**
     * 腾讯云配置
     */
    private QCloud qcloud;


    /**
     * 本地上传配置
     */
    private Local local;
}
