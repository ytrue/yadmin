package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 批量删除
     * <p>
     * delete from sys_role where role_id in
     * <foreach collection="roleIds" item="roleId" index="no" open="("
     * separator="," close=")">
     * #{roleId}
     * </foreach>
     *
     * @param roleIds
     */
    @Delete("<script>" + "delete from sys_role where role_id in\n" +
            "  \t\t<foreach collection=\"roleIds\" item=\"roleId\" index=\"no\" open=\"(\"\n" +
            "            separator=\",\" close=\")\">\n" +
            "            #{roleId}\n" +
            "        </foreach>" + "</script>")
    void deleteBatch(@Param("roleIds") Long[] roleIds);

    /**
     * 关联
     *
     * @param userId
     * @return
     */
    @Select("select role_id from sys_user_role where user_id = #{userId}")
    List<Long> listRoleIdByUserId(Long userId);

}
