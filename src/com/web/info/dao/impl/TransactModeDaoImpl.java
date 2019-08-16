package com.web.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.info.dao.TransactModeDao;
import com.web.info.model.TransactMode;

@Repository
public class TransactModeDaoImpl extends BaseDao<TransactMode> implements TransactModeDao {

	@Override
	public List<TransactMode> getList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select code, display_name, disorder from tf_dz_transact_mode t ");
		return super.listBySql(sql.toString(), TransactMode.class, true);
	}

}
