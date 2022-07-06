package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.system.model.SysRoleMenu;

import java.util.List;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色与菜单对应关系Service
*/
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
    * 分页查询
    * @param queryEntity
    * @return
    */
    IPage<SysRoleMenu> paginate(QueryEntity<SysRoleMenu> queryEntity);
}
