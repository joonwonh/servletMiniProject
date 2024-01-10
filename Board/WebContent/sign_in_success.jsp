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
	<h1>로그인 성공 !</h1>
	<h3><%=id %>님 환영합니다.</h3>
	
		<button type="button" onclick="location.href='sign_in.jsp'">게시판 가기</button>
		<button type="button" onclick="location.href='sign_up.jsp'">회원 정보 수정 하러 가기</button>
	
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