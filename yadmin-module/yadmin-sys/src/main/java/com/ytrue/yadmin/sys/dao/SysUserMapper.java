package com.ytrue.yadmin.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ytrue.yadmin.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 根据用户id 批量删除用户
     *
     * @param userIds
     * @param shopId
     */
    void deleteBatch(@Param("userIds") Long[] userIds, @Param("shopId") Long shopId);

    /**
     * 根据用户名获取管理员用户
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);

}
