<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locationHistory.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String strId = request.getParameter("userId");
		DBService.delete(Integer.parseInt(strId));
		response.sendRedirect("history-load.jsp");
	%>
</body>
</html>