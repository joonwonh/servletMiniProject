package com.addrbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.addrbook.model.AddrDto;
import com.addrbook.service.DeleteService;
import com.addrbook.service.DeleteServicempl;
import com.addrbook.service.InsertService;
import com.addrbook.service.InsertServicempl;
import com.addrbook.service.ListService;
import com.addrbook.service.ListServicempl;
import com.addrbook.service.UpdateService;
import com.addrbook.service.UpdateServicempl;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(command.equals("/list.do"))	{
			ListService listService = new ListServicempl();
			List<AddrDto> addrList = listService.execute(request,response);
			
			request.setAttribute("addrList", addrList);
//			response.sendRedirect("/addrBook_list.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addrbook_list.jsp");
			requestDispatcher.forward(request, response);
		}
		
		else if(command.equals("/insert.do"))	{
			AddrDto insAddr = new AddrDto();
			insAddr.setName(request.getParameter("name"));
			insAddr.setEmail(request.getParameter("email"));
			insAddr.setComdept(request.getParameter("comdept"));
			insAddr.setBirth(request.getParameter("birth"));
			insAddr.setTel(request.getParameter("tel"));
			insAddr.setMemo(request.getParameter("memo"));
			
			request.setAttribute("insAddr", insAddr);
			
			InsertService insertService = new InsertServicempl();
			insertService.execute(request, response);
			response.sendRedirect("/addrbook/list.do");
		}
		
		else if(command.equals("/update.do"))	{       
	        UpdateService updateService = new UpdateServicempl();
	        updateService.execute(request, response);
	        response.sendRedirect("/addrbook/list.do");
		}
		
		else if(command.equals("/delete.do"))	{		
	        DeleteService deleteService = new DeleteServicempl();
	        deleteService.execute(request, response);
	        response.sendRedirect("/addrbook/list.do");
		}
	}
}


