<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
    <% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 처리 페이지</title>
</head>
<body>
	<h2>***예외발생***</h2>
	메세지 내용: <%=exception.getMessage() %>
</body>
</html>