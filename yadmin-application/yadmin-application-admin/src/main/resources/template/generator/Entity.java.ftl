package ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
<#list imports as i>
import ${i!};
</#list>
<#if baseClassEntity??>
import ${baseClassEntity.packageName};
</#if>

/**
* @author ${author}
* @date ${date}
* @description ${tableComment}实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "${tableComment}")
@TableName("${tableName}")
public class ${ClassName}<#if baseClassEntity??> extends ${baseClassEntity.code}</#if> {
<#list columnList as column>

    <#if column.isPk>
    @TableId
    </#if>
    @TableField("${column.columnName}")
    <#if column.columnComment!?length gt 0>
    @ApiModelProperty(value = "${column.columnComment}")
    </#if>
    private ${column.attrType} ${column.attrName};

</#list>
}
