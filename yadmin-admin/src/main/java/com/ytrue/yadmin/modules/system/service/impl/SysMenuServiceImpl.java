package com.ytrue.yadmin.modules.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.dao.system.SysMenuDao;
import com.ytrue.yadmin.dao.system.SysRoleMenuDao;
import com.ytrue.yadmin.model.system.SysMenu;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    private final SysRoleMenuDao sysRoleMenuDao;

    private final SysMenuDao sysMenuDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenuAndRoleMenu(Long menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        sysRoleMenuDao.deleteByMenuId(menuId);
    }


    @Override
    public List<Long> listMenuIdByRoleId(Long roleId) {
        return sysMenuDao.listMenuIdByRoleId(roleId);
    }


    @Override
    public List<SysMenu> listChildrenMenuByParentId(Long parentId) {
        return sysMenuDao.listChildrenMenuByParentId(parentId);
    }

    @Override
    public List<Tree<String>> myMenuTree(Long userId, Integer deep) {
        //获得我的菜单
        List<SysMenu> myMenus = list();

        //配置HuTool的树
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setNameKey("name");
        treeNodeConfig.setIdKey("menuId");
        treeNodeConfig.setDeep(deep);

        //使用树
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        myMenus.forEach(menu -> {
            //扩展信息
            Map<String, Object> map = new HashMap<>();
            map.put("path", menu.getPath());
            map.put("icon", menu.getIcon());
            map.put("router", menu.getRouter());
            map.put("redirect", menu.getRedirect());
            map.put("authority", menu.getPerms());
            //插入进去
            nodeList.add(new TreeNode<>(
                    Convert.toStr(menu.getMenuId()),
                    Convert.toStr(menu.getParentId()),
                    menu.getName(),
                    menu.getOrderNum()).setExtra(map)
            );
        });

        return TreeUtil.build(nodeList, "0", treeNodeConfig, (treeNode, tree) -> {
            //基本信息
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            // 扩展属性 ...
            Map<String, Object> extra = treeNode.getExtra();
            tree.putExtra("path", extra.get("path"));
            tree.putExtra("icon", extra.get("icon"));
            tree.putExtra("router", extra.get("router"));
            tree.putExtra("redirect", extra.get("redirect"));
            tree.putExtra("authority", extra.get("authority"));
        });
    }
}
