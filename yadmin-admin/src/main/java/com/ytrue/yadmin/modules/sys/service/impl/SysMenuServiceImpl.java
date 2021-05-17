package com.ytrue.yadmin.modules.sys.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.modules.sys.constant.Constant;
import com.ytrue.yadmin.modules.sys.dao.SysMenuMapper;
import com.ytrue.yadmin.modules.sys.dao.SysRoleMenuMapper;
import com.ytrue.yadmin.modules.sys.service.dto.Meta;
import com.ytrue.yadmin.modules.sys.model.SysMenu;
import com.ytrue.yadmin.modules.sys.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ytrue
 */
@Service("sysMenuService")
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysMenuMapper sysMenuMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenuAndRoleMenu(Long menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        sysRoleMenuMapper.deleteByMenuId(menuId);
    }


    @Override
    public List<Long> listMenuIdByRoleId(Long roleId) {
        return sysMenuMapper.listMenuIdByRoleId(roleId);
    }


    @Override
    public List<SysMenu> listSimpleMenuNoButton() {
        return sysMenuMapper.listSimpleMenuNoButton();
    }

    @Override
    public List<SysMenu> listRootMenu() {
        return sysMenuMapper.listRootMenu();
    }

    @Override
    public List<SysMenu> listChildrenMenuByParentId(Long parentId) {
        return sysMenuMapper.listChildrenMenuByParentId(parentId);
    }

    @Override
    public List<SysMenu> listMenuAndBtn() {
        return sysMenuMapper.listMenuAndBtn();
    }

    @Override
    public List<Tree<String>> myRouter(Long userId) {

        return null;
        /*List<SysMenu> menus = listMenuByUserId(userId);
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setNameKey("name");
        treeNodeConfig.setIdKey("menuId");
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (SysMenu menu : menus) {
            Meta meta = new Meta(menu.getName(), menu.getIcon());
            Map<String, Object> map = new HashMap<>(16);

            if (menu.getType().equals(0)) {
                map.put("component", "Layout");
            } else {
                map.put("component", menu.getComponent());
            }
            map.put("path", menu.getUrl());
            map.put("meta", meta);
            map.put("hidden", menu.getHidden());
            nodeList.add(new TreeNode<>(
                    Convert.toStr(menu.getMenuId()),
                    Convert.toStr(menu.getParentId()),
                    menu.getName(),
                    menu.getOrderNum()).setExtra(map)
            );
        }
        return TreeUtil.build(nodeList, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            // 扩展属性 ...
            Map<String, Object> extra = treeNode.getExtra();
            tree.putExtra("path", extra.get("path"));
            tree.putExtra("component", extra.get("component"));
            tree.putExtra("meta", extra.get("meta"));
            tree.putExtra("hidden", extra.get("hidden"));
        });*/
    }


    /**
     * 获取用户菜单列表
     *
     * @param userId
     * @return
     */
    private List<SysMenu> listMenuByUserId(Long userId) {
        // 用户的所有菜单信息
        List<SysMenu> sysMenus;
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN_ID) {
            sysMenus = sysMenuMapper.listMenu();
        } else {
            sysMenus = sysMenuMapper.listMenuByUserId(userId);
        }
        return sysMenus;
    }

}
