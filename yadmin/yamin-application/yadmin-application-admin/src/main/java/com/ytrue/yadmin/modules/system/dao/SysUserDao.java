package com.ytrue.yadmin.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
    @Select("<select id=\"queryAllPerms\" resultType=\"string\">\n" +
            "\t\tselect m.perms from sys_user_role ur\n" +
            "\t\t\tLEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id\n" +
            "\t\t\tLEFT JOIN sys_menu m on rm.menu_id = m.menu_id\n" +
            "\t\twhere ur.user_id = #{userId}\n" +
            "\t</select>")
    List<String> queryAllPerms(Long userId);

    /**
     * 根据用户id 批量删除用户
     * <p>
     * delete from sys_user where user_id in
     * <foreach collection="userIds" item="userId" index="no" open="("
     * separator="," close=")">
     * #{userId}
     * </foreach>
     * and shop_id = #{shopId}
     *
     * @param userIds
     * @param shopId
     */
    @Delete("<script>" + "delete from sys_user where user_id in\n" +
            "  \t\t<foreach collection=\"userIds\" item=\"userId\" index=\"no\" open=\"(\"\n" +
            "            separator=\",\" close=\")\">\n" +
            "            #{userId}\n" +
            "        </foreach>\n" +
            "        and shop_id = #{shopId}" + "</script>")
    void deleteBatch(@Param("userIds") Long[] userIds, @Param("shopId") Long shopId);

}
