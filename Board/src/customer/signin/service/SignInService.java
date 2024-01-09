package customer.signin.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.signup.model.SignUpDAO;
import customer.signup.model.SignUpDTO;

public class SignInService {

	private SignUpDAO signUpDAO;
	
	public SignInService()	{
		signUpDAO = SignUpDAO.getInstance();
	}
	
	public boolean excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		SignUpDTO confirm = new SignUpDTO(id, pw); 
		
		return signUpDAO.conFirm(confirm);
		
		
		
	}
	
}
