package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色与菜单对应关系
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色ID数组，批量删除
     * <p>
     * delete from sys_role_menu where role_id in
     * <foreach item="roleId" collection="array" open="(" separator="," close=")">
     * #{roleId}
     * </foreach>
     *
     * @param roleIds
     * @return
     */
    @Delete("<script>" + "delete from sys_role_menu where role_id in\n" +
            "\t\t<foreach item=\"roleId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">\n" +
            "\t\t\t#{roleId}\n" +
            "\t\t</foreach>" + "</script>")
    int deleteBatch(Long[] roleIds);

    /**
     * 根据菜单id 删除菜单关联角色信息
     *
     * @param menuId
     */
    @Delete("delete from sys_role_menu where menu_id = #{menuId}")
    void deleteByMenuId(Long menuId);

    /**
     * 根据角色id 批量添加角色与菜单关系
     * <p>
     * insert into sys_role_menu (role_id,menu_id) values
     * <foreach collection="menuIdList" item="menuId" separator=",">
     * (#{roleId},#{menuId})
     * </foreach>
     *
     * @param roleId
     * @param menuIdList
     */
    @Insert("<script>" + "insert into sys_role_menu (role_id,menu_id) values\n" +
            "\t  \t<foreach collection=\"menuIdList\" item=\"menuId\" separator=\",\">\n" +
            "\t  \t\t(#{roleId},#{menuId})\n" +
            "\t  \t</foreach>" + "</script>")
    void insertRoleAndRoleMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);
}
