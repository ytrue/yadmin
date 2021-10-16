package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * <select id="queryAllPerms" resultType="string">
     * select m.perms from sys_user_role ur
     * LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id
     * LEFT JOIN sys_menu m on rm.menu_id = m.menu_id
     * where ur.user_id = #{userId}
     * </select>
     *
     * @param userId 用户ID
     * @return
     */
    @Select("SELECT" +
            "\tm.perms \n" +
            "FROM\n" +
            "\tsys_user_role ur\n" +
            "\tLEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id\n" +
            "\tLEFT JOIN sys_menu m ON rm.menu_id = m.menu_id \n" +
            "WHERE\n" +
            "\tur.user_id = #{userId}")
    List<String> queryAllPerms(Long userId);

}
