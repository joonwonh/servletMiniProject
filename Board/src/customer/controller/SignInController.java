package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		SignInService signin = new SignInService(); 
		boolean isConfirm = signin.excute(request, response);
		if(isConfirm == true)	{
			RequestDispatcher success = request.getRequestDispatcher("/board.jsp");
			success.forward(request, response);
		}
		else {
			RequestDispatcher fail = request.getRequestDispatcher("/sign_in_fail.jsp");
			fail.forward(request, response);
		}
		
	}

}
