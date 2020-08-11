package ${entity.actionPackageName};

import ${entity.entityPackageName}.${entity.className};
import ${entity.paramPackage}.${entity.className}Param;
import ${entity.dtoPackageName}.${entity.className}Dto;
import ${entity.servicePackageName}.${entity.className}Service;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.tao.frameworks.base.common.Result;
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

    @GetMapping("/list")
    @ApiOperation(value = "列表", notes = "列表")
    public Result list(${entity.className}Param param) {
        return this.${entity.classInstanceName}Service.list(param);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加", notes = "添加")
    public void add(@RequestBody ${entity.className} ${entity.classInstanceName}) {
        this.${entity.classInstanceName}Service.add(${entity.classInstanceName});
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    public void delete(@PathVariable ${entity.idSimpleType} ${entity.idName}) {
        this.${entity.classInstanceName}Service.deleteById(${entity.idName});
    }

    @PutMapping("/edit")
    @ApiOperation(value = "修改", notes = "修改")
    public void edit(${entity.className} ${entity.classInstanceName}) {
        this.${entity.classInstanceName}Service.edit(${entity.classInstanceName});
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "详情", notes = "详情")
    public ${entity.className}Dto get(@PathVariable ${entity.idSimpleType} ${entity.idName}) {
        return this.${entity.classInstanceName}Service.getById(${entity.idName});
    }


}