<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.javaEdu.dto.MemberDto" %>
<%@ page import="com.javaEdu.command.MemberService" %>
<%@ page import="com.javaEdu.command.MembersAllService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management</title>
</head>
<body>
<%
	MemberService service = new MembersAllService();
	List<MemberDto> dtos = service.execute(request, response);
	for(int i=0; i<dtos.size(); i++)	{
		MemberDto dto = dtos.get(i);
		String id = dto.getId();
		String pw = dto.getPw();
		String name = dto.getName();
		String email = dto.getEmail();
		Timestamp rDate = dto.getrDate();
		String address = dto.getAddress();
	%>
	<%=id %>, <%=pw %>, <%=name %>, <%=email %>, <%=rDate.toLocalDateTime() %>, <%=address %> <hr/> 
	<%
	}
%>
</body>
</html>