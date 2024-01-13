package com.javaEdu.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaEdu.dao.MemberDao;
import com.javaEdu.dto.MemberDto;

public class MembersAllService implements MemberService {

	@Override
	public List<MemberDto> execute(HttpServletRequest request, HttpServletResponse response) {

		MemberDao dao = MemberDao.getInstance();
		return dao.membersAll();
	
	}
	
}
