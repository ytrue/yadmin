package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ytrue.yadmin.modules.system.model.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统配置信息
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 根据角色id获取菜单列表
     *
     * @param roleId 角色id
     * @return 菜单id列表
     */
    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Long> listMenuIdByRoleId(Long roleId);

}
