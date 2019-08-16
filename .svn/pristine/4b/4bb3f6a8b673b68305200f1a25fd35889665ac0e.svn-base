package com.web.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.info.dao.DzFaultDao;
import com.web.info.model.DzFault;

@Repository
public class DzFaultDaoIml extends BaseDao<DzFault> implements DzFaultDao {

	@Override
	public List<DzFault> getList() {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select code, display_name, sort_keyword from tf_dz_fault t ");
		return super.listBySql(sql.toString(), DzFault.class, true);
	}

}
