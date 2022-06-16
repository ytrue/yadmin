package ${package}<#if moduleName??>.${moduleName}</#if>.service<#if subModuleName??>.${subModuleName}</#if>;

import com.baomidou.mybatisplus.extension.service.IService;
import ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>.${ClassName};

import java.util.List;

/**
* @author ${author}
* @date ${date}
* @description ${tableComment}Service
*/
public interface ${ClassName}Service extends IService<${ClassName}> {

}
