package com.ytrue.yadmin.sys.service;


import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.sys.model.SysMenu;


import java.util.List;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 菜单管理
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 删除 菜单，与角色菜单之间的关系
     *
     * @param menuId 菜单id
     */
    void deleteMenuAndRoleMenu(Long menuId);

    /**
     * 根据角色ID，获取菜单列表
     *
     * @param roleId 角色id
     * @return 角色所拥有的菜单id列表
     */
    List<Long> listMenuIdByRoleId(Long roleId);


    /**
     * 根据一级菜单id 获取二级菜单
     *
     * @param parentId 一级菜单id
     * @return 二级菜单列表
     */
    List<SysMenu> listChildrenMenuByParentId(Long parentId);


    /**
     * 获得当前用户的菜单,封装成树结构
     *
     * @param userId 用户名id
     * @param deep   深度
     * @return
     */
    List<Tree<String>> myMenuTree(Long userId, Integer deep);
}
