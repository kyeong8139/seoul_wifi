<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookmark_group.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
	<%
	try {
		if (request.getParameter("groupName") != null 
				&& request.getParameter("groupOrder") != null) {
			String groupName = request.getParameter("groupName");
			int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
			Group group = new Group();
			group.setName(groupName);
			group.setOrderRow(groupOrder);
			DBService.insert(group);
			
			response.sendRedirect("group-load.jsp");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
    out.write("<h1>북마크 그룹 추가</h1>");
    out.write("<a href='index.jsp'>홈</a> | " 
            + "<a href='history-load.jsp'>위치 히스토리 목록</a> | "
            + "<a href='seoulwifi-load.jsp'>Open API 와이파이 정보 가져오기</a> | ");
    out.write("<a href='mark-load.jsp'>북마크 보기</a> | ");
    out.write("<a href='group-load.jsp'>북마크 그룹 관리</a> <br><br>");
    
    out.write("<table id='customersLeft'>");
    out.write("<tr>");
    out.write("<th>북마크 이름</th>");
    out.write("<td> <input type='text' id='groupName'> </td>");
    out.write("</tr>");
    out.write("<tr>");
    out.write("<th>순서</th>");
    out.write("<td> <input type='text' id='groupOrder'> </td>");
    out.write("</tr>");
    out.write("<tr>");
    out.write("<td style='text-align: center;' colspan=2> <button onclick='groupInsert()'>추가</button> </td>");
    out.write("</tr>");
	%>
	
	<script type="text/javascript">	
	
		function groupInsert() {
			let groupName = document.getElementById('groupName').value;
			let groupOrder = document.getElementById('groupOrder').value;
			
			window.location.href = "group-insert.jsp?groupName=" + document.getElementById('groupName').value 
					+ "&groupOrder=" + document.getElementById('groupOrder').value;
		}

	</script>
	
	
</body>
</html>