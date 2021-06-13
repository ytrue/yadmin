package com.ytrue.yadmin.tool.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description Local 七牛云配置
 */
@Data
public class Qiniu {

    private String bucket;

    private String accessKey;

    private String secretKey;

    private String domain;
}
