package customer.signup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.signup.model.SignUpDAO;
import customer.signup.model.SignUpDTO;

public class InsertCustomerService {

	private SignUpDAO signUpDAO;

	

	public InsertCustomerService() {
		signUpDAO = SignUpDAO.getInstance();
	}



	public void execute(HttpServletRequest request, HttpServletResponse response)	{
		
		SignUpDTO insCust = (SignUpDTO) request.getAttribute("insCust");
		
		signUpDAO.insertCustomer(insCust);
		
	}
	
}
