package com.ytrue.yadmin.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.modules.sys.dao.SysRoleMapper;
import com.ytrue.yadmin.modules.sys.dao.SysRoleMenuMapper;
import com.ytrue.yadmin.modules.sys.dao.SysUserRoleMapper;
import com.ytrue.yadmin.modules.sys.model.SysRole;
import com.ytrue.yadmin.modules.sys.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private final SysRoleMenuMapper sysRoleMenuMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoleAndRoleMenu(SysRole role) {
        role.setCreateTime(DateUtil.date());
        this.save(role);
        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }
        //保存角色与菜单关系
        sysRoleMenuMapper.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAndRoleMenu(SysRole role) {
        // 更新角色
        sysRoleMapper.updateById(role);
        //先删除角色与菜单关系
        sysRoleMenuMapper.deleteBatch(new Long[]{role.getRoleId()});
        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }
        //保存角色与菜单关系
        sysRoleMenuMapper.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        sysRoleMapper.deleteBatch(roleIds);
        //删除角色与菜单关联
        sysRoleMenuMapper.deleteBatch(roleIds);
        //删除角色与用户关联
        sysUserRoleMapper.deleteBatch(roleIds);
    }

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        return sysRoleMapper.listRoleIdByUserId(userId);
    }

}
