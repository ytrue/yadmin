package com.ytrue.yadmin.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.sys.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    void deleteBatch(@Param("roleIds") Long[] roleIds);

    List<Long> listRoleIdByUserId(Long userId);

}
