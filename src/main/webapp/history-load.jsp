<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locationHistory.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
	<%
    out.write("<h1>와이파이 정보 구하기</h1>");
    out.write("<a href='index.jsp'>홈</a> | " 
            + "<a href='history-load.jsp'>위치 히스토리 목록</a> | "
            + "<a href='seoulwifi-load.jsp'>Open API 와이파이 정보 가져오기</a> | ");
    out.write("<a href='mark-load.jsp'>북마크 보기</a> | ");
    out.write("<a href='group-load.jsp'>북마크 그룹 관리</a> <br><br>");
    
    ArrayList<Location> list = DBService.select();
    
    out.write("<table id='customers'>");
    out.write("<tr style='background-color: #04AA6D; color: #FFFFFF; text-align: center;'>");
    out.write("<th>ID</th>");
    out.write("<th>X좌표</th>");
    out.write("<th>Y좌표</th>");
    out.write("<th>조회일자</th>");
    out.write("<th>비고</td>");
    out.write("</tr>");
    for (Location location : list) {
    	out.write("<tr>");
        out.write("<td>" + location.getUserId() + "</td>");
        out.write("<td>" + location.getUserX() + "</td>");
        out.write("<td>" + location.getUserY() + "</td>");
        out.write("<td>" + location.getRegisterDate() + "</td>");
        out.write("<td style='text-align: center;'> <button onclick='deleteHistory(" + location.getUserId() + ")'>삭제</button> </td>");
        out.write("</tr>");	
    }
    out.write("</table>");
	%>
	
	<script type="text/javascript">
		function deleteHistory(userId) {
			window.location.href = "history-delete.jsp?userId=" + userId;
		}
	</script>
</body>
</html>