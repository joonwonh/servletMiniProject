package com.addrbook.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDto;

public interface UpdateService {

	List<AddrDto> execute(HttpServletRequest request, HttpServletResponse response);
}
