<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div align="center">
		<h1>회원 수정</h1>
		<hr>
		<a href="list.do">돌아가기</a>
		<form action="update.do" name="editForm" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<table>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name"  value="<%=adb.getName()%>" autofocus /></td>
					</tr>
					<tr>
						<td>email</td>
						<td><input type="email" name="email"  value="<%=adb.getEmail()%>" ></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="tel"  value="<%=adb.getTel()%>" ></td>
					</tr>
					<tr>
						<td>생일</td>
						<td><input type="text" name="birth"  value="<%=adb.getBirth()%>" ></td>
					</tr>
					<tr>
						<td>회사</td>
						<td><input type="text" name="comdept"  value="<%=adb.getComdept()%>" ></td>
					</tr>
					<tr>
						<td>메모</td>
						<td><input type="text" name="memo"  value="<%=adb.getMemo()%>" ></td>
					</tr>
					<tr>
						<td><input type="submit" value="수정완료" /></td>
						<td><input type="reset" value="취소" /></td>
						<td><a href="delete.do">삭제</a></td>
					</tr>
			</table>
		</form>
	</div>
</body>
</html>