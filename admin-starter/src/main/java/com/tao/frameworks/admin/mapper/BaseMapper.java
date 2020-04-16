package com.tao.frameworks.admin.mapper;

/**
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * 也支持 写 mapper.xml 文件
 *
 * @author tao
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

}