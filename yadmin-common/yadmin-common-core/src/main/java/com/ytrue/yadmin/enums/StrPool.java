package com.ytrue.yadmin.enums;

/**
 * @author ytrue
 * @date 2021/8/27 09:58
 * @description 字符串,统一管理
 */
public enum StrPool {

    /**
     * 处理
     */
    YOU_CANT_BE_YOUR_SUPERIOR("自己不能是自己的上级"),
    MENU_TYPE_CAN_ONLY_BE_HEADER_MENU("菜单类型只能是头部菜单"),
    PLEASE_SELECT_THE_CORRESPONDING_MENU_TYPE("请选择对应的菜单类型"),
    THE_USER_ALREADY_EXISTS("该用户已存在"),
    SUBCLASS_EXISTS_AND_CANNOT_BE_DELETED("当前分类下存在子分类,不允许删除"),
    DATA_DOES_NOT_EXIST("数据不存在");




    /**
     * 前端进行页面展示的信息
     */
    private final String message;

    StrPool(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
