<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사람인</title>
</head>
<body>
<div id ="li">
<c:forEach var="saram" items="${sarams}" >
회사 : ${saram.companyName}<br>
제목 : ${saram.title}<br>
링크 : <a href="${saram.url}">${saram.url}</a><br> 
<br><br><br><hr>
</c:forEach>
</div>
</body>
</html>