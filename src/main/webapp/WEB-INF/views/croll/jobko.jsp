<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잡코리아</title>
</head>
<body>
<div id ="limit">
<c:forEach var="jobkorea" items="${jobkoreas}" >
회사 : ${jobkorea.companyName}<br>
제목 : ${jobkorea.title}<br>
링크 : <a href="${jobkorea.url}">${jobkorea.url}</a><br> 
<br><br><br><hr>
</c:forEach>
</div>
</body>
</html>