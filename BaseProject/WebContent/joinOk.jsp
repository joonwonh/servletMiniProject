<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="com.javaEdu.dto.MemberDto"/>
<jsp:setProperty name="dto" property="*"/>
    <%@page import="com.javaEdu.dao.MemberDao" %>
    <%@page import="java.sql.Timestamp" %>
<%
	dto.setrDate(new Timestamp(System.currentTimeMillis()));
	MemberDao dao = MemberDao.getInstance();
	if(dao.confirmId(dto.getId()) == 1)	{
	%>
		<script language="javascript">
			alert("아이디가 이미 존재합니다.");
			history.back();
		</script>
<%				
	}else	{
		int ri = dao.insertMember(dto);
		if(ri == 1)	{
			session.setAttribute("id", dto.getId());
%>
		<script language="javascript">
			alert("회원 가입을 축하합니다.");
			document.location.href="login.jsp";
			</script>
<%
		}else	{
			%>
		<script language="javascript">
			alert("회원가입에 실패했습니다.");
			document.location.href="login.jsp";
		</script>
			
<%
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>

</body>
</html>