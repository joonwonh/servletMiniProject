package com.javaEdu.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaEdu.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> execute(HttpServletRequest request, HttpServletResponse response);
}
