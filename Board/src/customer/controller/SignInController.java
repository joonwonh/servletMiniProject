package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.signin.service.SignInService;

@WebServlet("/signIn")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doHandle(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doHandle(request, response);
		
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		SignInService signin = new SignInService(); 
		boolean isConfirm = signin.excute(request, response);
		String id = request.getParameter("id");
		if(isConfirm == true)	{
			session.setAttribute("id", id);
			RequestDispatcher success = request.getRequestDispatcher("/sign_in_success.jsp");
			success.forward(request, response);
		}
		else {
			RequestDispatcher fail = request.getRequestDispatcher("/sign_in_fail.jsp");
			fail.forward(request, response);
		}
		
	}

}
