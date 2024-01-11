package com.javaEdu.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doHandle(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doHandle(request,response);
		
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		if(command.equals("/membersAll.do"))	{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head></head><body>");
			
			Service service = new MembersAllService();
			List <MemberDto> dto = service.execute(request,response);
			
			for(int i=0; i<dto.size(); i++)	{
				MemberDto dtos = dto.get(i);
				String id = dtos.getId();
				String pw = dtos.getPw();
				String name = dtos.getName();
				String email = dtos.getEmail();
				Timestamp rDate = dtos.getrDate();
				String address = dtos.getAddress();
				
				out.println(id + ", " + pw + ", " + name + ", " + email + ", " + rDate.toLocalDateTime() + ", " + address + "<hr>");
			}
			out.println("</body></html>");
		}
	}

}
