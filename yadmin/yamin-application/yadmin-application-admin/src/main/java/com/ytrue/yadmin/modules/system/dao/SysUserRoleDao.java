package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 用户与角色对应关系
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 根据角色ID数组，批量删除
     * <p>
     * delete from sys_user_role where role_id in
     * <foreach item="roleId" collection="roleIds" open="(" separator="," close=")">
     * #{roleId}
     * </foreach>
     *
     * @param roleIds
     * @return
     */
    @Delete("<script>" + "delete from sys_user_role where role_id in\n" +
            "\t\t<foreach item=\"roleId\" collection=\"roleIds\" open=\"(\" separator=\",\" close=\")\">\n" +
            "\t\t\t#{roleId}\n" +
            "\t\t</foreach>" + "</script>")
    int deleteBatch(@Param("roleIds") Long[] roleIds);

    /**
     * 根据用户id删除用户与角色关系
     *
     * @param userId
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     * 根据用户id 批量添加用户角色关系
     * <p>
     * insert into sys_user_role (user_id,role_id) values
     * <foreach collection="roleIdList" item="roleId" separator=",">
     * (#{userId},#{roleId})
     * </foreach>
     *
     * @param userId
     * @param roleIdList
     */
    @Insert("<script>" + "\tinsert into sys_user_role (user_id,role_id) values\n" +
            "\t  \t<foreach collection=\"roleIdList\" item=\"roleId\" separator=\",\">\n" +
            "\t  \t\t(#{userId},#{roleId})\n" +
            "\t  \t</foreach>" + "</script>")
    void insertUserAndUserRole(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);
}
