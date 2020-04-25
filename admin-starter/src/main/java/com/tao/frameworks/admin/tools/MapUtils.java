package com.tao.frameworks.admin.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map工具
 *
 * @author tao
 */
public class MapUtils {

    /**
     * 获取所有字段
     *
     * @param cls 实体类Class
     * @return
     */
    public static List<Field> getFields(Class<?> cls) {
        List<Field> fields = new ArrayList<>();
        if (cls == Object.class) {
            return new ArrayList<>();
        }
        fields.addAll(getFields(cls.getSuperclass()));
        Field[] declaredFields = cls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            fields.add(declaredField);
        }
        return fields;
    }

    /**
     * 获取属性名称转换数据库字段
     * 驼峰转换
     *
     * @param name
     * @return
     */
    private static String getName(String name) {
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : name.toCharArray()) {
            if (Character.isUpperCase(c)) {
                stringBuffer.append("_");
                stringBuffer.append(Character.toLowerCase(c));
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 类转Map
     * 属性值为null的忽略
     * @param cls
     * @return
     */
    public static Map<String, Object> beanToMap(Object cls) {
        Map<String, Object> fieldMap = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        List<Field> fields = getFields(cls.getClass());
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers()) && !"currentUser".equals(field.getName())) {
                stringBuffer.append("\n").append(getName(field.getName()));
                try {
                    field.setAccessible(true);
                    Object value = field.get(cls);
                    if (value != null && !value.equals("")) {
                        fieldMap.put(getName(field.getName()), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(fieldMap.keySet().size()==0){
            return null;
        }
        return fieldMap;
    }
}
