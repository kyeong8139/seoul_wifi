<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    out.write("<form action='location-load.jsp' id='locationForm' style='display: inline;'>");
    out.write("LAT: <input type='text' name='userLat' id='userLat'> , ");
    out.write("LNT: <input type='text' name='userLnt' id='userLnt'> ");
    out.write("<input type='hidden' name='timestamp' id='timestamp'>");
    out.write("<button onclick='getPosition()'>내 위치 가져오기</button> ");
    out.write("</form>");
    out.write("<button onclick='getNearWifi()'>근처 WIFI 정보 보기</button> <br>");
    
    out.write("<table id='customers'>");
    out.write("<tr>");
    out.write("<th>거리(Km)</th>");
    out.write("<th>관리번호</th>");
    out.write("<th>자치구</th>");
    out.write("<th>와이파이명</th>");
    out.write("<th>도로명주소</th>");
    out.write("<th>상세주소</th>");
    out.write("<th>설치위치(층)</th>");
    out.write("<th>설치유형</th>");
    out.write("<th>설치기관</th>");
    out.write("<th>서비스구분</th>");
    out.write("<th>망종류</th>");
    out.write("<th>설치년도</th>");
    out.write("<th>실내외구분</th>");
    out.write("<th>WIFI접속환경</th>");
    out.write("<th>X좌표</th>");
    out.write("<th>Y좌표</th>");
    out.write("<th>작업일자</th>");
    out.write("</tr>");
    out.write("<tr>");
    out.write("<td colspan=17 style='text-align: center;'> <br>위치 정보를 입력한 후에 조회해 주세요. <br><br> </td>");
    out.write("</tr>");
    out.write("</table>");
    
    %>
	
	<script type="text/javascript">
		
		function initialPositionSetting() {
			let initialLat = '<%= request.getParameter("userLat") == null ? "0.0" : request.getParameter("userLat") %>';
			let initialLnt = '<%= request.getParameter("userLnt") == null ? "0.0" : request.getParameter("userLnt") %>';

		 	document.getElementById('userLat').value = initialLat;
		 	document.getElementById('userLnt').value = initialLnt;
		}
	
		initialPositionSetting();
		
	
		function getPosition() {
			navigator.geolocation.getCurrentPosition(positionUpdate, positionError);
		}
		
		function positionUpdate(position) {
			let time = new Date();
			let userLat = position.coords.latitude;
			let userLnt = position.coords.longitude;
			
			let year = time.getFullYear();
			let month = ('0' + (time.getMonth() + 1)).slice(-2);
			let day = ('0' + time.getDate()).slice(-2);
			let hours = ('0' + time.getHours()).slice(-2); 
			let minutes = ('0' + time.getMinutes()).slice(-2);
			let seconds = ('0' + time.getSeconds()).slice(-2);
			
			let timestamp = year + "-" + month + "-" + day + "T" + hours + ":" + minutes + ":" + seconds;
			
			
			document.getElementById('userLat').value = userLat;
            document.getElementById('userLnt').value = userLnt;
            document.getElementById('timestamp').value = timestamp;
            
            document.getElementById('locationForm').submit();
		}
		
		function positionError(errer) {
			console.error('위치 정보 오류', error);
		}
		
		function getNearWifi() {
		    window.location.href = "nearwifi-load.jsp?userLat=" + document.getElementById('userLat').value + "&userLnt=" + document.getElementById('userLnt').value;
		}


	</script>
</body>
</html>