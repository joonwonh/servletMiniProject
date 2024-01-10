<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
    <%
    	String id = request.getParameter("id");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">게시판</h1>
	<h3><%=id %>님</h3>
	
	<%
		String context = request.getContextPath();
		String url = request.getRequestURL().toString();
		String mapping = request.getServletPath();
		String uri = request.getRequestURI();
	%>
	<h3>컨택스트 이름 :     <%=context %></h3><br>
	<h3>전체 경로 :      <%=url %></h3><br >
	<h3>매핑 이름 :      <%=mapping%></h3><br>
	<h3>uri :      <%=uri %></h3>
</body>
</html>