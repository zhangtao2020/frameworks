package ${entity.actionPackageName};

import ${entity.entityPackageName}.${entity.className};
import ${entity.paramPackage}.${entity.className}Param;
import ${entity.dtoPackageName}.${entity.className}Dto;
import ${entity.servicePackageName}.${entity.className}Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import com.tao.frameworks.admin.tools.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @author ${entity.author} on ${entity.createTime}
*/
@RestController
@RequestMapping("/${entity.classInstanceName}")
@Api(tags = "API接口")
public class ${entity.className}Controller {

    @Resource
    private ${entity.className}Service ${entity.classInstanceName}Service;

    @GetMapping(value = "/list", name = "列表")
    @ApiOperation(value = "列表", notes = "列表")
    public Result list(${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectPage(page, limit, params);
    }

    @PostMapping(value = "/insert", name = "插入")
    public void insert(${entity.className} ${entity.classInstanceName}) {
        this.${entity.classInstanceName}Service.insert(${entity.classInstanceName});
    }

    @GetMapping(value = "/delete", name = "删除")
    public void delete(${entity.idSimpleType} ${entity.idName}) {
        this.${entity.classInstanceName}Service.deleteById(${entity.idName});
    }

    @PostMapping(value = "/update", name = "修改")
    public void update(${entity.className} ${entity.classInstanceName}) {
        this.${entity.classInstanceName}Service.update(${entity.classInstanceName});
    }

    @GetMapping(value = "/get", name = "详情")
    public ${entity.className}Dto get(${entity.idSimpleType} ${entity.idName}) {
        return this.${entity.classInstanceName}Service.getById(${entity.idName});
    }

    @PostMapping(value = "/query", name = "分页查询")
    public Result query(int page, int limit, ${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectPage(page, limit, params);
    }


}