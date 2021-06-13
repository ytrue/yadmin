package com.ytrue.yadmin.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.sys.dao.SysRoleDao;
import com.ytrue.yadmin.sys.dao.SysRoleMenuDao;
import com.ytrue.yadmin.sys.dao.SysUserRoleDao;
import com.ytrue.yadmin.sys.model.SysRole;
import com.ytrue.yadmin.sys.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 角色
 *
 * @author lgh
 */
@Service("sysRoleService")
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    private final SysRoleMenuDao sysRoleMenuDao;
    private final SysUserRoleDao sysUserRoleDao;
    private final SysRoleDao SysRoleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoleAndRoleMenu(SysRole role) {
        role.setCreateTime(DateUtil.date());
        this.save(role);
        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }
        //保存角色与菜单关系
        sysRoleMenuDao.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAndRoleMenu(SysRole role) {
        // 更新角色
        SysRoleDao.updateById(role);
        //先删除角色与菜单关系
        sysRoleMenuDao.deleteBatch(new Long[]{role.getRoleId()});
        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }
        //保存角色与菜单关系
        sysRoleMenuDao.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        SysRoleDao.deleteBatch(roleIds);
        //删除角色与菜单关联
        sysRoleMenuDao.deleteBatch(roleIds);
        //删除角色与用户关联
        sysUserRoleDao.deleteBatch(roleIds);
    }

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        return SysRoleDao.listRoleIdByUserId(userId);
    }

}
