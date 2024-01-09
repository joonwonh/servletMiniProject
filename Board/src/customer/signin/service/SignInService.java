package customer.signin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.signup.model.SignUpDAO;
import customer.signup.model.SignUpDTO;

public class SignInService {

	private SignUpDAO signUpDAO;
	
	public SignInService()	{
		signUpDAO = SignUpDAO.getInstance();
	}
	
	public void excute(HttpServletRequest request, HttpServletResponse response)	{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		SignUpDTO confirm = new SignUpDTO(id, pw); 
		
		signUpDAO.conFirm(confirm);
		
	}
	
}
