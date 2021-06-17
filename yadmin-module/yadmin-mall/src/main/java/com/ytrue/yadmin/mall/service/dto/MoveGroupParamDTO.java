package com.ytrue.yadmin.mall.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/17 14:42
 * @description 图片移动分组参数 dto
 */
@Data
public class MoveGroupParamDTO {

    /**
     * 文件集合
     */
    private List<Integer> fileIds;

    /**
     * 组id
     */
    private Integer groupId;
}
