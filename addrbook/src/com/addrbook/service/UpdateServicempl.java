package com.addrbook.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDao;
import com.addrbook.model.AddrDto;

public class UpdateServicempl implements UpdateService {
//	private AddrDao addrDao;

//    public UpdateServicempl() {
//    	addrDao = AddrDao.getInstance();
//    }

	@Override
	public List<AddrDto> execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String birth = request.getParameter("birth");
        String comdept = request.getParameter("comdept");
        String memo = request.getParameter("memo");

        AddrDto updatedAddr = new AddrDto(id, name, email, comdept, birth, tel, memo);

        AddrDao addrDao = AddrDao.getInstance();
        addrDao.updateAddr(updatedAddr);
		return null;
	}


}
