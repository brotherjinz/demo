package com.web.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.dao.TransactModeDao;
import com.web.info.model.TransactMode;
import com.web.info.service.TransactModeService;

@Service
public class TransactModeServiceImpl implements TransactModeService {
	
	@Autowired
	private TransactModeDao transactModeDao;
	
	@Override
	public List<TransactMode> queryList() {
		// TODO Auto-generated method stub
		return transactModeDao.getList();
	}

}
