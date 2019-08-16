package com.web.info.dao;

import java.util.List;

import com.web.info.model.TransactMode;

public interface TransactModeDao extends IBase<TransactMode> {
	List<TransactMode> getList();
}
