package com.addrbook.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.addrbook.model.AddrDao;

public class DeleteServicempl implements DeleteService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 
		try{	
	        HttpSession session = request.getSession();
	        int id = (int) session.getAttribute("updDelld");
			 AddrDao addrDao = AddrDao.getInstance();
		     addrDao.delAddr(id);
		}catch(NumberFormatException e) {
            e.printStackTrace(); 
        }
	}


}
