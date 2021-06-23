package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色与菜单对应关系
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);

    /**
     * 根据菜单id 删除菜单关联角色信息
     *
     * @param menuId
     */
    void deleteByMenuId(Long menuId);

    /**
     * 根据角色id 批量添加角色与菜单关系
     *
     * @param roleId
     * @param menuIdList
     */
    void insertRoleAndRoleMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);
}
