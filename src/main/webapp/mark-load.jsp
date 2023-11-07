<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark.*"%>
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
    out.write("<h1>북마크 목록</h1>");
    out.write("<a href='index.jsp'>홈</a> | " 
            + "<a href='history-load.jsp'>위치 히스토리 목록</a> | "
            + "<a href='seoulwifi-load.jsp'>Open API 와이파이 정보 가져오기</a> | ");
    out.write("<a href='mark-load.jsp'>북마크 보기</a> | ");
    out.write("<a href='group-load.jsp'>북마크 그룹 관리</a> <br><br>");
    
    ArrayList<Bookmark> list = DBService.selectAll();
    
    out.write("<table id='customers'>");
    out.write("<tr>");
    out.write("<th>ID</th>");
    out.write("<th>북마크 이름</th>");
    out.write("<th>와이파이명</th>");
    out.write("<th>등록일자</th>");
    out.write("<th>비고</th>");
    out.write("</tr>");
    for (Bookmark bookmark : list) {
    	String groupName = bookmark_group.DBService.select(bookmark.getGroupId()).getName();
    	String wifiName = seoulWifi.DBService.selectWifi(bookmark.getMgrNo()).getX_swifi_main_nm();
    	out.write("<tr>");
        out.write("<td>" + bookmark.getMarkId() + "</td>");
        out.write("<td>" + groupName + "</td>");
        out.write("<td> <a href='nearwifi-detail.jsp?mgr_no=" + bookmark.getMgrNo() + "'>" + wifiName+ "</a> </td>");
        out.write("<td>" + bookmark.getRegisterDate() + "</td>");
        out.write("<td style='text-align: center;'> <a href='mark-delete.jsp?markId=" + bookmark.getMarkId() + "&check=false'>삭제</a> </td>");
        out.write("</tr>");	
    }
    out.write("</table>");
	%>
	
	<script type="text/javascript">
		function deleteMark(markId) {
			window.location.href = "mark-delete.jsp?markId=" + markId
					+"&check=false";
		}
	</script>
</body>
</html>