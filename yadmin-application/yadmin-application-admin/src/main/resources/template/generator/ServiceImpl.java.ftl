package ${package}<#if moduleName??>.${moduleName}</#if>.service.impl<#if subModuleName??>.${subModuleName}</#if>;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>.${ClassName};
import ${package}<#if moduleName??>.${moduleName}</#if>.dao<#if subModuleName??>.${subModuleName}</#if>.${ClassName}Dao;
import ${package}<#if moduleName??>.${moduleName}</#if>.service<#if subModuleName??>.${subModuleName}</#if>.${ClassName}Service;

import org.springframework.stereotype.Service;

/**
* @author ${author}
* @date ${date}
* @description ${tableComment}Service实现类
*/
@Service
public class ${ClassName}ServiceImpl extends ServiceImpl<${ClassName}Dao, ${ClassName}> implements ${ClassName}Service {
}
