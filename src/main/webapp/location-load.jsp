<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locationHistory.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		String userLat = request.getParameter("userLat");
		String userLnt = request.getParameter("userLnt");
		String timestamp = request.getParameter("timestamp");
			
		if (!userLat.equals("0.0") && !userLnt.equals("0.0")) {
			locationHistory.DBService.insert(userLat, userLnt, timestamp);
			seoulWifi.DBService.updateDistance(Double.parseDouble(userLat), Double.parseDouble(userLnt));
		}
			
		response.sendRedirect("index.jsp?userLat=" + userLat + "&userLnt=" + userLnt);
	%>

</body>
</html>