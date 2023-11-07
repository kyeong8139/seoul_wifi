<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
	String groupId = request.getParameter("groupId");
	String mgrNo = request.getParameter("mgrNo");
	
	Bookmark bookmark = new Bookmark();
	bookmark.setGroupId(Integer.parseInt(groupId));
	bookmark.setMgrNo(mgrNo);
	
	DBService.insert(bookmark);
	response.sendRedirect("mark-load.jsp");

	%>
	
	
</body>
</html>