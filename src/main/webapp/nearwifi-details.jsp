<%@page import="bookmark_group.Group"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="seoulWifi.*"%>
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
   		
		String mgr_no = request.getParameter("mgr_no");
		Wifi wifi = DBService.selectWifi(mgr_no);
		
		ArrayList<Group> groups = bookmark_group.DBService.selectAll();
		out.write("<select id='groupId'>");
		out.write("<option style='background-color: #306C50; color:#FFFFFF;' value='select'>북마크 그룹 이름 선택</option>");
		for (Group group : groups) {
			out.write("<option style='background-color: #306C50; color:#FFFFFF;' value='"+ group.getId() +"'>"+ group.getName() +"</option>");
		}
		out.write("</select> ");
		out.write("<button onclick=markInsert('"+ wifi.getX_swifi_mgr_no() +"')>북마크 추가하기</button>");
		
		
	    out.write("<table id='customersLeft'>");
	    
	    out.write("<tr>");
	    out.write("<th>거리(Km)</th>");
	    out.write("<td>" + wifi.getDistance()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>관리번호</th>");
	    out.write("<td>" + wifi.getX_swifi_mgr_no()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>자치구</th>");
	    out.write("<td>" + wifi.getX_swifi_wrdofc() + "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>와이파이명</th>");
	    out.write("<td> <a href='nearwifi-details.jsp?mgr_no=" + wifi.getX_swifi_mgr_no() + "'>" + wifi.getX_swifi_main_nm()+ "</a> </td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>도로명주소</th>");
	    out.write("<td>" + wifi.getX_swifi_adres1()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>상세주소</th>");
	    out.write("<td>" + wifi.getX_swifi_adres2()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>설치위치(층)</th>");
	    out.write("<td>" + wifi.getX_swifi_instl_floor()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>설치유형</th>");
	    out.write("<td>" + wifi.getX_swifi_instl_ty()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>설치기관</th>");
	    out.write("<td>" + wifi.getX_swifi_instl_mby()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>서비스구분</th>");
	    out.write("<td>" + wifi.getX_swifi_svc_se()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>망종류</th>");
	    out.write("<td>" + wifi.getX_swifi_cmcwr()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>설치년도</th>");
	    out.write("<td>" + wifi.getX_swifi_cnstc_year()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>실내외구분</th>");
	    out.write("<td>" + wifi.getX_swifi_inout_door()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>WIFI접속환경</th>");
	    out.write("<td>" + wifi.getX_swifi_remars3()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>X좌표</th>");
	    out.write("<td>" + wifi.getLat()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>Y좌표</th>");    
	    out.write("<td>" + wifi.getLnt()+ "</td>");
	    out.write("</tr>");
	    
	    out.write("<tr>");
	    out.write("<th>작업일자</th>");
	    out.write("<td>" + wifi.getWork_dttm()+ "</td>");
	    out.write("</tr>");

	    
	    out.write("</table>");

	%>
	
		<script type="text/javascript">	
	
		function markInsert(mgrNo) {
	        let groupId = document.getElementById('groupId').value;

	        if (groupId !== 'select') {
	            window.location.href = "mark-insert.jsp?groupId=" + groupId + "&mgrNo=" + mgrNo;
	        } else {
	            alert("북마크 그룹을 선택하세요.");
	        }
	    }


	</script>
</body>
</html>