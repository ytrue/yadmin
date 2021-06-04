package com.ytrue.yadmin.sys.constant;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 菜单类型
 */
public enum MenuType {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private final int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
