package com.ytrue.yadmin.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.model.system.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 批量删除
     * @param roleIds
     */
    void deleteBatch(@Param("roleIds") Long[] roleIds);

    /**
     * 关联
     * @param userId
     * @return
     */
    List<Long> listRoleIdByUserId(Long userId);

}
