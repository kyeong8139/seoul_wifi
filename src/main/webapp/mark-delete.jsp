<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
	<%
		String strId = request.getParameter("markId");
		String check = request.getParameter("check");
		
		if (check.equals("true")) {
			DBService.delete(Integer.parseInt(strId));
			response.sendRedirect("mark-load.jsp");
		}
		
		
	    out.write("<h1>북마크 삭제</h1>");
	    out.write("<a href='index.jsp'>홈</a> | " 
	            + "<a href='history-load.jsp'>위치 히스토리 목록</a> | "
	            + "<a href='seoulwifi-load.jsp'>Open API 와이파이 정보 가져오기</a> | ");
	    out.write("<a href='mark-load.jsp'>북마크 보기</a> | ");
	    out.write("<a href='group-load.jsp'>북마크 그룹 관리</a> <br><br>");
		
		Bookmark bookmark = DBService.select(Integer.parseInt(strId));	
	
		out.write("<p>북마크를 삭제하시겠습니까?</p>");
		
		out.write("<table id='customersLeft'>");
		
	    out.write("<tr>");
	    out.write("<th>북마크 이름</th>");
	    String groupName = bookmark_group.DBService.select(bookmark.getGroupId()).getName();
	    out.write("<td>" + groupName+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>와이파이명</th>");
	    String wifiName = seoulWifi.DBService.selectWifi(bookmark.getMgrNo()).getX_swifi_main_nm();
	    out.write("<td>" + wifiName+ "</td>");
	    out.write("</tr>");
	
	    out.write("<tr>");
	    out.write("<th>등록일자</th>");
	    out.write("<td>" + bookmark.getRegisterDate()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<td colspan=2 style='text-align: center;'>");
	    out.write("<a href='mark-load.jsp'>돌아가기</a> | <button onclick='deleteCheck("+ strId +")'>삭제</button>");
	    out.write("</td>");
	    out.write("</tr>");
	%>
	
	<script>
		function deleteCheck(markId) {
			window.location.href = "mark-delete.jsp?markId=" + markId
			+"&check=true";
	}
	</script>
</body>
</html>