<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 창</title>
</head>
<body>
  <form action="insertMember.do" name="insMem" method="post">
    <table>
      <th>회원 가입창</th>
      <tr>
        <td>아이디</td>
        <td><input type="text" name="id"></td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td><input type="password" name="pw"></td>
      </tr>
      <tr>
        <td>이름</td>
        <td><input type="text" name="name"></td>
      </tr>
      <tr>
        <td>이메일</td>
        <td><input type="email" name="email"></td>
      </tr>
    </table>
    <input type="submit" value="가입하기"/>
    <input type="reset" value="다시입력">
  </form>
</body>
</html>