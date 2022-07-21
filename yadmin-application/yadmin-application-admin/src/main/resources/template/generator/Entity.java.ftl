package ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
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
public class ${ClassName}<#if baseClassEntity??> extends ${baseClassEntity.code}</#if> implements Serializable {

<#list columnList as column>

    <#if column.isPk>
    @TableId
    <#else >
    @TableField("${column.columnName}")
    </#if>
    <#if column.columnComment!?length gt 0>
    @ApiModelProperty(value = "${column.columnComment}")
    </#if>
    private ${column.attrType} ${column.attrName};

</#list>
}
