package com.addrbook.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDao;
import com.addrbook.model.AddrDto;


public class ListServicempl implements ListService {

	@Override
	public List<AddrDto> execute(HttpServletRequest request, HttpServletResponse response) {
        AddrDao addrDao = AddrDao.getInstance();
        List<AddrDto> addrList = addrDao.getList();

        return addrList;
	}


}
