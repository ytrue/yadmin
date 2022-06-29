package ${package}<#if moduleName??>.${moduleName}</#if>.service<#if subModuleName??>.${subModuleName}</#if>;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>.${ClassName};

import java.util.List;

/**
* @author ${author}
* @date ${date}
* @description ${tableComment}Service
*/
public interface ${ClassName}Service extends IService<${ClassName}> {

    /**
    * 分页查询
    * @param queryEntity
    * @return
    */
    IPage<${ClassName}> paginate(QueryEntity<${ClassName}> queryEntity);
}
