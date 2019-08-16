package com.web.info.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.info.dao.IBase;
import com.web.info.system.PageBar;
import com.web.info.system.PageResult;
import com.web.info.system.Pager;
import com.web.info.system.SystemContext;

/**
 * base-dao 实现
 * 
 * @author sunxiaorong
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BaseDao<T> implements IBase<T> {

	private SessionFactory sessionFactory;
	private Class<?> clz;

	public Class<?> getClz() {
		if (clz == null) {
			// 获取泛型的Class对象
			clz = ((Class<?>) (((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T load(Object id) {
		return (T) getSession().load(getClz(), (Serializable) id);
	}

	@Override
	public T get(Object id) {
		return (T) getSession().get(getClz(), (Serializable) id);
	}

	public List<T> list(String hql, Object[] args) {
		return list(hql, args, null);
	}

	public List<T> list(String hql, Object arg) {
		return list(hql, new Object[] { arg });
	}

	public List<T> list(String hql) {
		return list(hql, null);
	}

	private String initSort(String hql) {
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if (sort != null && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (!"desc".equals(order))
				hql += " asc";
			else
				hql += " desc";
		}
		return hql;
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					// 查询条件是列表
					query.setParameterList(key, (Collection) val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args) {
		if (args != null && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return list(hql, null, alias);
	}

	public Pager<T> find(String hql, Object[] args) {
		return find(hql, args, null);
	}

	public Pager<T> find(String hql, Object arg) {
		return find(hql, new Object[] { arg });
	}

	public Pager<T> find(String hql) {
		return find(hql, null);
	}

	@SuppressWarnings("rawtypes")
	private void setPagers(Query query, Pager pages) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if (pageOffset == null || pageOffset < 0)
			pageOffset = 0;
		if (pageSize == null || pageSize < 0)
			pageSize = 15;
		pages.setOffset(pageOffset);
		pages.setPageSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}

	private String getCountHql(String hql, boolean isHql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) " + e;
		if (isHql)
			c.replaceAll("fetch", "");
		return c;
	}

	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		String cq = getCountHql(hql, true);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		// 设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		// 设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		Pager<T> pages = new Pager<T>();
		setPagers(query, pages);
		List<T> datas = query.list();
		pages.setRows(datas);
		long total = (Long) cquery.uniqueResult();
		pages.setTotal(total);
		pages.setTotalPage((total + pages.getPageSize() - 1) / pages.getPageSize());
		pages.setCurrentPage(SystemContext.getCurrentPage());
		return pages;
	}

	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return find(hql, null, alias);
	}

	public Object queryObject(String hql, Object[] args) {
		return queryObject(hql, args, null);
	}

	public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return queryObject(hql, null, alias);
	}

	public Object queryObject(String hql, Object arg) {
		return queryObject(hql, new Object[] { arg });
	}

	public Object queryObject(String hql) {
		return queryObject(hql, null);
	}

	public void updateBySql(String sql, Object[] args) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, args);
		query.executeUpdate();
	}

	public void updateBySql(String sql, Object arg) {
		updateBySql(sql, new Object[] { arg });
	}

	public void updateBySql(String sql) {
		updateBySql(sql, null);
	}

	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	public void updateByHql(String hql, Object arg) {
		updateByHql(hql, new Object[] { arg });
	}

	public void updateByHql(String hql) {
		updateByHql(hql, null);
	}

	public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
		return listBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
		return listBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
		return listBySql(sql, null, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if (hasEntity) {
			sq.addEntity(clz);
		} else
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		return sq.list();
	}

	public <N extends Object> List<N> listByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		return listBySql(sql, null, alias, clz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
		return findBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
		return findBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {
		return findBySql(sql, null, clz, hasEntity);
	}

	public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql);
		String cq = getCountHql(sql, false);
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		setParameter(sq, args);
		setParameter(cquery, args);
		Pager<N> pages = new Pager<N>();
		setPagers(sq, pages);
		if (hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setRows(datas);
		// ((BigInteger)cquery.uniqueResult()).longValue();
		long total = listBySql(sql, args, clz, hasEntity).size();
		pages.setTotal(total);
		pages.setTotalPage((total + pages.getPageSize() - 1) / pages.getPageSize());
		pages.setCurrentPage(SystemContext.getCurrentPage());
		return pages;
	}

	public <N extends Object> Pager<N> findByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		return findBySql(sql, null, alias, clz, hasEntity);
	}

	public Object queryObjectBySql(String sql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createSQLQuery(sql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectBySql(String sql, Object args, Map<String, Object> alias) {
		return queryObjectBySql(sql, new Object[] { args }, alias);
	}

	public Object queryObjectBySql(String sql, Object args) {
		return queryObjectBySql(sql, new Object[] { args }, null);
	}

	public Object queryObjectBySql(String sql, Object[] args) {
		return queryObjectBySql(sql, args, null);
	}

	public Object queryObjectBySql(String sql) {
		return queryObjectBySql(sql, null, null);
	}

	public Object queryObjectBySqlAlias(String sql, Map<String, Object> alias) {
		return queryObjectBySql(sql, null, alias);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public Object addEntity(Object entity) {
		return getSession().save(entity);
	}

	@Override
	public void updateEntity(Object entity) {
		getSession().update(entity);
	}

	@Override
	public void deleteEntity(Object entity) {
		getSession().delete(entity);
	}

	@Override
	public Object loadEntity(Class<?> clz, Object id) {
		return getSession().load(clz, (Serializable) id);
	}

	@Override
	public Object getEntity(Class<?> clz, Object id) {
		return getSession().get(clz, (Serializable) id);
	}

	@Override
	public PageResult getPageListSql(String pageNum, String pageSize, String sql) throws Exception {
		PageResult result = new PageResult();// easy_like_baidu_20101111_liu
		PageBar pu = new PageBar();
		Session session = null;
		try {
			// long time1=System.currentTimeMillis();
			session = getSession();
			int size = Integer.parseInt(session.createSQLQuery(pu.getCountSql(sql)).list().get(0).toString() + "");
			// System.out.println("END size:"+String.valueOf(System.currentTimeMillis()-time1));
			result.setPageTool(pu.getPageTool(pageNum, pageSize, size));
			result.setPageSize(pageSize != null ? Integer.parseInt(pageSize) : 0);
			result.setPageNumber(pageNum != null ? Integer.parseInt(pageNum) : 0);
			result.setTotalRow(size);
			/*System.out.println("pageNum:" + pageNum);
			System.out.println("pageSize:" + pageSize);
			System.out.println("size:" + size);
			System.out.println(sql);*/
			List<Object> list = session.createSQLQuery(pu.getPageListSql(sql)).list();
			// System.out.println("END:"+String.valueOf(System.currentTimeMillis()-time1));
			result.setPageList(list);

			// this.releaseSession(session);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// if (session!=null)this.releaseSession(session);
		}
	}

	/**
	 * 执行单独query sql方法
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public List<Object> queryBySql(String sql) {
		return getSession().createSQLQuery(sql).list();
	}

	@Override
	public int excuteBySql(String sql) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/* 查询数据库中当前时间 */
	@Override
	public Date getNowDate() {
		List<Object> list = this.queryBySql("select to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') from dual");

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(0).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取数据库中明天
	 * 
	 * @return
	 */
	@Override
	public Date getTomorrow() {
		List<Object> list = this.queryBySql("select to_char(sysdate+1,'yyyyMMdd') from dual");

		try {
			return new SimpleDateFormat("yyyyMMdd").parse(list.get(0).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取数据库中昨天
	 * 
	 * @return
	 */
	@Override
	public Date getYesterDay() {
		List<Object> list = this.queryBySql("select to_char(sysdate-1,'yyyyMMdd') from dual");

		try {
			return new SimpleDateFormat("yyyyMMdd").parse(list.get(0).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
