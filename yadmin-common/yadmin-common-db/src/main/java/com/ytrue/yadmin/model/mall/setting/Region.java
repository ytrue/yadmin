package com.ytrue.yadmin.model.mall.setting;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/9/6 17:22
 * @description 省市区数据表
 */
@Data
@TableName("region")
public class Region implements Serializable {

    private static final long serialVersionUID = 6305538045600134630L;
    
    /**
     * 区划信息ID
     */
    @TableId
    @TableField("id")
    private Integer id;
    /**
     * 区划名称
     */
    @TableField("name")
    private String name;
    /**
     * 父级ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 区划编码
     */
    @TableField("code")
    private String code;
    /**
     * 层级(1省级 2市级 3区/县级)
     */
    @TableField("level")
    private Integer level;


}
