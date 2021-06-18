package com.ytrue.yadmin.tools.module.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description   腾讯云云配置
 */
@Data
public class QCloud {

    private String bucket;

    private String region;

    private String secretId;

    private String secretKey;

    private String domain;
}
