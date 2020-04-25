package ${entity.servicePackageName};

import org.springframework.stereotype.Service;
import ${entity.daoPackageName}.${entity.className}Mapper;
import ${entity.entityPackageName}.${entity.className};
import ${entity.dtoPackageName}.${entity.className}Dto;
import ${entity.paramPackage}.${entity.className}Param;
import com.tao.frameworks.admin.tools.Result;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.frameworks.admin.tools.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;

/**
 * @author ${entity.author} on ${entity.createTime}
 */
@Service
public class ${entity.className}Service {

    @Resource
    private ${entity.className}Mapper ${entity.classInstanceName}Mapper;

    /**
     * 插入
     *
     * @param ${entity.classInstanceName} 实体对象
     */
    public int insert(${entity.className} ${entity.classInstanceName}){
        return ${entity.classInstanceName}Mapper.insert(${entity.classInstanceName});
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    public int deleteById(${entity.idSimpleType} ${entity.idName}){
        return ${entity.classInstanceName}Mapper.deleteById(${entity.idName});
    }

    /**
     * 修改
     *
     * @param ${entity.classInstanceName} 实体对象
     */
    public int update(${entity.className} ${entity.classInstanceName}){
        return ${entity.classInstanceName}Mapper.updateById(${entity.classInstanceName});
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return
     */
    public ${entity.className}Dto getById(${entity.idSimpleType} ${entity.idName}){
        ${entity.className} ${entity.classInstanceName} = ${entity.classInstanceName}Mapper.selectById(${entity.idName});
        if(${entity.classInstanceName}!=null){
            ${entity.className}Dto ${entity.classInstanceName}Dto = new ${entity.className}Dto();
            BeanUtils.copyProperties(${entity.classInstanceName}, ${entity.classInstanceName}Dto);
            return ${entity.classInstanceName}Dto;
        }
        return null;
    }

    /**
     * 条件查询所有
     *
     * @param param
     * @return
     */
    public List<${entity.className}Dto> selectByMap(${entity.className}Param param) {
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        List<${entity.className}> ${entity.classInstanceName}List = ${entity.classInstanceName}Mapper.selectByMap(columnMap);
        List<${entity.className}Dto> ${entity.classInstanceName}DtoList = ${entity.classInstanceName}List.stream().map(${entity.classInstanceName} -> {
            ${entity.className}Dto ${entity.classInstanceName}Dto = new ${entity.className}Dto();
            BeanUtils.copyProperties(${entity.classInstanceName}, ${entity.classInstanceName}Dto);
            return ${entity.classInstanceName}Dto;
        }).collect(Collectors.toList());
        return ${entity.classInstanceName}DtoList;
    }

    /**
     * 条件查询并分页
     *
     * @param page
     * @param limit
     * @param param
     * @return
     */
    public Result selectPage(int page, int limit, ${entity.className}Param param) {
        QueryWrapper<${entity.className}> queryWrapper = new QueryWrapper<>();
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        if(columnMap!=null) {
            columnMap.entrySet().forEach(entry -> queryWrapper.eq(entry.getKey(), entry.getValue()));
        }
        IPage<${entity.className}> p = new Page<>(page, limit);
        IPage<${entity.className}> ipage = ${entity.classInstanceName}Mapper.selectPage(p, queryWrapper);
        List<${entity.className}> ${entity.classInstanceName}List = ipage.getRecords();
        List<${entity.className}Dto> ${entity.classInstanceName}DtoList = ${entity.classInstanceName}List.stream().map(${entity.classInstanceName} -> {
            ${entity.className}Dto ${entity.classInstanceName}Dto = new ${entity.className}Dto();
            BeanUtils.copyProperties(${entity.classInstanceName}, ${entity.classInstanceName}Dto);
            return ${entity.classInstanceName}Dto;
        }).collect(Collectors.toList());
        int total = (int)ipage.getTotal();
        Result result = new Result();
        result.setData(${entity.classInstanceName}DtoList);
        result.setCount(total);
        return result;
    }

}