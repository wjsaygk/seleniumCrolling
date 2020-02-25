<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function saramin() {
		$.ajax({
			type : 'get',
			url : '/saramfind',
			dataType : 'json',
			success : function(data) {				
				console.log(data);
				$('#saramin_list').show();
				$('#jobkorea_list').hide();
				salist(data);
			},
			error : function(request, status, error) {
				console.log(err);
			}
		});
};

function jobkorea() {
	$.ajax({
		type : 'get',
		url : '/jobfind',
		dataType : 'json',
		success : function(data) {				
			console.log(data);
			$('#jobkorea_list').show();
			$('#saramin_list').hide();
			 joblist(data)
		},
		error : function(request, status, error) {
			console.log(err);
		}
	});
};

	
	function salist(data){
	      for(var s of data){
	         var saram_in =
	        	 "<div id='saramin_list'><div class='card' style='width:800px'><div class='card-body'><h4 class='card-title'>회사 : "+s.companyName+" </h4><p class='card-text'>제목 : "+s.title+"</p><a href='/saramInformation?url="+s.url+"' class='btn btn-primary'>url : "+s.url+"</a></div></div></div><br>";
	         console.log(saram_in);
	         $("#saramin_list").append(saram_in);
	      }
	      
	   }

	function joblist(data){
	      for(var j of data){
	         var job_korea =
	        	 "<div id='jobkorea_list'><div class='card' style='width:800px'><div class='card-body'><h4 class='card-title'>회사 : "+j.companyName+" </h4><p class='card-text'>제목 : "+j.title+"</p><a href='/jobInformation?url="+j.url+"' class='btn btn-primary'>url : "+j.url+"</a></div></div></div><br>";
	         console.log(job_korea);
	         $("#jobkorea_list").append(job_korea);
	      }
	      
	   }
</script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-light navbar-light">
  <ul class="navbar-nav">
    <li class="nav-item active">
     <button onclick="saramin()">사람인 채용공고 보기</button>
    </li>
    <li class="nav-item">
     <button onclick="jobkorea()">잡코리아 채용공고 보기</button> 
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/managerloginForm">관리자 페이지로 이동</a>
    </li>
   
  </ul>
</nav>




<div id="saramin_list">
<%-- <c:forEach var="saram" items="${sarams}" >
  <div class="card" style="width:600px">
    <div class="card-body">
      <h4 class="card-title">회사 : ${saram.companyName} </h4>
      <p class="card-text">제목 : ${saram.title}</p>
      <a href="/saramInformation" class="btn btn-primary">url : ${saram.url}</a>
    </div>
  </div>  
  </c:forEach> --%>
  </div>
  <br> 

<div id="jobkorea_list">
<%-- <c:forEach var="jobkorea" items="${jobkoreas}" >
  <div class="card" style="width:600px">
    <div class="card-body">
      <h4 class="card-title">회사 : ${jobkorea.companyName} </h4>
      <p class="card-text">제목 : ${jobkorea.title}</p>
      <a href="#" class="btn btn-primary">url : ${jobkorea.url}</a>
    </div>
  </div>  
  </c:forEach> --%>
  </div>
  <br>

</body>
</html>