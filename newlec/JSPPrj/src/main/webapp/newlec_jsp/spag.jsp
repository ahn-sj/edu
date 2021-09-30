<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- -------------------------------------------- -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");
%>
<body>
	${requestScope.result}입니다. <br>
	${names[1]}입니다.<br>
	${notice.title}입니다.<br>
	${result}<br>
	${param.n}<br>
	${header.accept}<br>
	${empty param.n?'값이 부족합니다':param.n}<br>
</body>
</html>