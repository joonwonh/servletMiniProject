<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Board</title>
</head>
<body>
    <table width="500" cellpadding="0" border="2">
        <tr>
            <td>번호</td>
            <td>이름</td>
            <td>제목</td>
            <td>날짜</td>
            <td>히트</td>
        </tr>

        <c:forEach items="${list}" var="dto">   <!--list를 변수명 dto로 하나씩 arrayList를 기리키고 있음-->
            <tr>
                <td>${dto.bId}</td>
                <td>${dto.bName}</td>
                <td>
                    <c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
                    <a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
                </td>
                <td>${dto.bDate}</td>
                <td>${dto.bHit}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5"><a href="write_view.do">글 작성</a></td>
        </tr>
    </table>
</body>
</html>