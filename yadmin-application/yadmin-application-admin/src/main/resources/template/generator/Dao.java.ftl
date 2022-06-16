package ${package}<#if moduleName??>.${moduleName}</#if>.dao<#if subModuleName??>.${subModuleName}</#if>;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>.${ClassName};


/**
* @author ${author}
* @date ${date}
* @description ${tableComment}Dao
*/
public interface ${ClassName}Dao extends BaseMapper<${ClassName}> {

}
