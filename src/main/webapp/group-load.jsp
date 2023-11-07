<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark_group.*" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
	<%
    out.write("<h1>북마크 그룹</h1>");
    out.write("<a href='index.jsp'>홈</a> | " 
            + "<a href='history-load.jsp'>위치 히스토리 목록</a> | "
            + "<a href='seoulwifi-load.jsp'>Open API 와이파이 정보 가져오기</a> | ");
    out.write("<a href='mark-load.jsp'>북마크 보기</a> | ");
    out.write("<a href='group-load.jsp'>북마크 그룹 관리</a> <br><br>");
		
		ArrayList<Group> list = DBService.selectAll();
		
		out.write("<button onclick='insertGroup()'>북마크 그룹 이름 추가</button>");
		
		out.write("<table id='customers'>");
	    out.write("<tr>");
	    out.write("<th>ID</th>");
	    out.write("<th>북마크 이름</th>");
	    out.write("<th>순서</th>");
	    out.write("<th>등록일자</th>");
	    out.write("<th>수정일자</th>");
	    out.write("<th>비고</th>");
	    out.write("</tr>");
	    for (Group group : list) {
	    	out.write("<tr>");
	        out.write("<td>" + group.getId() + "</td>");
	        out.write("<td>" + group.getName() + "</td>");
	        out.write("<td>" + group.getOrderRow() + "</td>");
	        out.write("<td>" + group.getRegisterDate() + "</td>");
	        out.write("<td>" + (group.getEditDate() == null ? ' ' : group.getEditDate())+ "</td>");
	        out.write("<td style='text-align: center;'> <a href='group-update.jsp?id=" + group.getId() + "'>수정</a> <a href='group-delete.jsp?id=" + group.getId() + "'>삭제</a> </td>");
	        out.write("</tr>");	
	    }
	    out.write("</table>");
	%>
	
	<script type="text/javascript">
		function insertGroup() {
			window.location.href = "group-insert.jsp";
		}
	</script>
</body>
</html>