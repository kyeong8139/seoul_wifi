<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark_group.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String strId = request.getParameter("id");
		DBService.delete(Integer.parseInt(strId));
		response.sendRedirect("group-load.jsp");
	%>
</body>
</html>