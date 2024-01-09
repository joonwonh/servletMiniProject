package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.signup.model.SignUpDTO;
import customer.signup.service.InsertCustomerService;


@WebServlet("/insertMember.do")
public class SignUpController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
			
			SignUpDTO insCust = new SignUpDTO();
			insCust.setId(request.getParameter("id"));
			insCust.setPw(request.getParameter("pw"));
			insCust.setName(request.getParameter("name"));
			insCust.setEmail(request.getParameter("email"));
			
			request.setAttribute("insCust", insCust);
			
			InsertCustomerService insertService = new InsertCustomerService();
			insertService.execute(request, response);
			
			response.sendRedirect("/Board/sign_up_success.jsp");
		}
		
	}

