package com.example.myvideorecoderlib.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

 /**
  *
  * @ClassName:      ReflectObjectUtils
  * @Description:    通过反射获取配置类的属性值
  * @Author:         lyx253071
  */
public class ReflectObjectUtils {

    /**
     * 获取类或接口中所有的常量
     *
     * @param clazz
     * @return 返回包含属性名和属性值的集合 map
     */
    public static Map<String, Object> getKeyValues(Class<?> clazz) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f:fields) {
            String name = null;
            Object obj = null;
            try {
                name = f.getName();
                obj = clazz.getField(name).get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if(name!=null) map.put(name,obj);

        }
        return map;

    }
}
