package com.javaEdu.ex;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {

	List<MemberDto> execute(HttpServletRequest request, HttpServletResponse response);

}
