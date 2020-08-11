package ${entity.paramPackage};

import lombok.Data;
import lombok.experimental.Accessors;
import com.tao.frameworks.base.common.BaseParam;

<#if entity.hasSetType>
    import java.util.Set;
</#if>
<#if entity.hasDateType>
    import java.util.Date;
</#if>
<#if entity.hasDecimalType>
    import java.math.BigDecimal;
</#if>
import io.swagger.annotations.ApiModelProperty;

/**
* @author ${entity.author} on ${entity.createTime}
*/
@Data
@Accessors(chain = true)
public class ${entity.className}Param extends BaseParam {

    /**
    * id
    */
    @ApiModelProperty("id")
    private ${entity.idSimpleType} ${entity.idName};
<#list entity.propList as prop>

    /**
    * ${prop.note}
    */
    @ApiModelProperty("${prop.note}")
    private ${prop.simpleType} ${prop.propName};
</#list>
}