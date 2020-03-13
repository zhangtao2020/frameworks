package ${entity.actionPackageName};

import ${entity.entityPackageName}.${entity.className};
import ${entity.paramPackage}.${entity.className}Param;
import ${entity.servicePackageName}.${entity.className}Service;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/select", name = "接口")
    public List<${entity.className}> selectByMap(${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectByMap(params);
    }

    @GetMapping(value = "/query", name = "接口")
    public IPage<${entity.className}> query(int page, int size, ${entity.className}Param params) {
        return this.${entity.classInstanceName}Service.selectPage(page, size, params);
    }

}