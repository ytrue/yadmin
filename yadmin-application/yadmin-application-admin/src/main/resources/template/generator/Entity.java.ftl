package ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;

/**
* @author ${author}
* @date ${date}
* @description ${tableComment}实体类
*/
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("${tableName}")
public class ${ClassName}<#if baseClassEntity??> extends ${baseClassEntity.code}</#if> {
<#list columnList as column>

</#list>
}
