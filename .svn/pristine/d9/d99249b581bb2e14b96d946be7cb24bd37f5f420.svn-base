package com.web.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.info.dao.DzFaultDao;
import com.web.info.model.DzFault;
import com.web.info.service.DzFaultService;

@Service
public class DzFaultServiceImpl implements DzFaultService {

	@Autowired
	private DzFaultDao dzFaultDao;
	
	@Override
	public List<DzFault> queryList() {
		// TODO Auto-generated method stub
		return dzFaultDao.getList();
	}

}
