package com.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * 版权所有：2018-CDHA  
 * 项目名称：elelockData     
 *  
 * 类描述：  json工具类
 * 类名称：com.web.util.JsonToBean       
 * 创建人：   
 * 创建时间：2018年5月30日 下午3:03:53     
 * 修改人：  
 * 修改时间：2018年5月30日 下午3:03:53     
 * 修改备注：     
 * @version   V1.0
 */
public class JsonToBean<T> {
	@SuppressWarnings("rawtypes")
	protected Class a = null;

	/**
	 * 解析json数据生成T
	 * 
	 * @param result
	 *            json数据，注意属性名称和json的key保持一致
	 * @param flag
	 *            值为1:bean对象属性名称全为大写，2：bean对象属性名称全为小写，否则json的key值已经和属性名称完全一致
	 * @return
	 */
	public JsonToBean(T t) {
		a = t.getClass();
	}

	@SuppressWarnings("unchecked")
	public T getBeanByJson(String result, int flag) {
		T bean = null;
		if (result == null || result.isEmpty()) {
			return null;
		}
		try {
			JSONObject json = JSONObject.fromObject(result);//result.replace("null", "\"\"")
			JSONObject jsonObject = new JSONObject();
			Iterator it = json.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = json.get(key);
				if (flag == 1) {// 转为大写
					jsonObject.put(key.toUpperCase(), value);
				} else if (flag == 2) {// 转为小写
					jsonObject.put(key.toLowerCase(), value);
				} else {
					jsonObject.put(key, value);
				}

			}

			bean = (T) JSONObject.toBean(jsonObject, a);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("json数据转换为Bean对象失败");
		}
		return bean;
	}

	public List<T> getBeanByJsonList(String result, int isUpCase) {// isUpCase 为1 是字段名为大写 2 是字段名为小写
		// T bean=null;
		if (result == null || result.isEmpty()) {
			return null;
		}
		List<T> list = null;
		try {
			list = new ArrayList<T>();
			JSONArray jsonArr = JSONArray.fromObject(result);
			Iterator<JSONObject> iterator = jsonArr.iterator();
			while (iterator.hasNext()) {
				JSONObject json = iterator.next();
				T t = null;
				t = getBeanByJson(json.toString(), isUpCase);
				list.add(t);
			}
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("json数据转换为Bean对象失败");
		}
		return list;
	}

}
