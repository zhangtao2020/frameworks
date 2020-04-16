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
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
* @author ${entity.author} on ${entity.createTime}
*/
@RestController
@RequestMapping("/${entity.classInstanceName}")
public class ${entity.className}Controller {

    @Resource
    private ${entity.className}Service ${entity.classInstanceName}Service;

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
    public IPage<${entity.className}> query(int page, int limit, ${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectPage(page, limit, params);
    }

    @PostMapping(value = "/select", name = "查询所有")
    public List<${entity.className}> select(${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectByMap(params);
    }

}