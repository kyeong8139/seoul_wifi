<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="seoulWifi.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		DBService.load();
		int count = DBService.countRow();
			
		out.write("<h1 style='text-align:center;'>" + count +"개의 WIFI 정보를 정상적으로 저장하였습니다.</h1> <br>");
		
		out.write("<div style='text-align: center;'>");
		out.write("<a href='index.jsp'>홈 으로 가기</a>");
		out.write("</div>");
	%>
</body>
</html>