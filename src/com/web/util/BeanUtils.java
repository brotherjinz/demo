package com.web.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: BeanUtils
 * @Description:TODO(工具类，判断bean，数组，集合是否为空)
 * @author: zml
 * @date: 2014-4-18 上午10:20:26
 *
 */
public class BeanUtils {

	public static boolean isBlank(Object obj){
		if(obj == null){
			return true;
		}
		return false;
	}
	
	public static boolean isBlank(List list){
		if(list == null || list.size()<=0){
			return true;
		}
		return false;
	}
	public static boolean isBlank(Map map){
		if(map == null || map.size()<=0){
			return true;
		}
		return false;
	}
	public static boolean isBlank(Object []obj){
		if(obj == null || obj.length<=0){
			return true;
		}
		return false;
	}
	
	public static void copyPropertiesIgnoreNull(Object source,Object target){
		Method[] ms = source.getClass().getMethods();
		ArrayList<String> nullMethod = new ArrayList<String>();
		try {
			for (Method method : ms) {
				String name = method.getName();
				if(name.startsWith("get")) {
					if(method.invoke(source)==null){
						String field = name.substring(3, 4).toLowerCase()+name.substring(4);
						nullMethod.add(field);
					}
				}
			}
			org.springframework.beans.BeanUtils.copyProperties(source, target, nullMethod.toArray(new String[nullMethod.size()]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
