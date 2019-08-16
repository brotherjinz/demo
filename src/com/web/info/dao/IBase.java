package com.web.info.dao;

import java.util.Date;
import java.util.List;

import com.web.info.system.PageResult;

/**
 * @Description: dao层公共接口(功能描述)
 * @author 张明虎 zhangminghu@yuntengzhiyong.com
 * @date 2014年12月4日 上午12:37:23
 */
public interface IBase<T> {
	/**
	 * 添加对象
	 * 
	 * @param t
	 * @return
	 */
	public T add(T t);

	/**
	 * 更新对象
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 删除对象
	 * 
	 * @param t
	 */
	public void delete(T t);

	/**
	 * 查询对象 延迟加载
	 * 
	 * @param id
	 * @return
	 */
	public T load(Object id);

	/**
	 * 查询对象 非延迟
	 * 
	 * @param id
	 * @return
	 */
	public T get(Object id);

	/**
	 * 添加一个实体 非泛型
	 * 
	 * @param entity
	 * @return
	 */
	public Object addEntity(Object entity);

	/**
	 * 修改一个实体 非泛型
	 * 
	 * @param entity
	 * @return
	 */
	public void updateEntity(Object entity);

	/**
	 * 删除一个实体 非泛型
	 * 
	 * @param entity
	 * @return
	 */
	public void deleteEntity(Object entity);

	/**
	 * 获取一个实体 非泛型 延迟加载
	 * 
	 * @param entity
	 * @return
	 */
	public Object loadEntity(Class<?> clz, Object id);

	/**
	 * 获取一个实体 非泛型 非延迟加载
	 * 
	 * @param entity
	 * @return
	 */
	public Object getEntity(Class<?> clz, Object id);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public PageResult getPageListSql(String pageNum, String pageSize, String sql) throws Exception;

	public Date getNowDate();

	/**
	 * 获取数据库中明天
	 * 
	 * @return
	 */
	public Date getTomorrow();

	/**
	 * 获取数据库中昨天
	 * 
	 * @return
	 */
	public Date getYesterDay();

	public List<Object> queryBySql(String sql);

	public int excuteBySql(String sql);
	
}
