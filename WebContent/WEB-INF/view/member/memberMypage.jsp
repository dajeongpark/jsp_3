<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootstrap.jsp"/>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
	<div class="container-fluid">
		<div class="row">
		  <h1>ID : ${member.id}</h1>
		  <h1>NAME : ${member.name}</h1>
		  <h1>EMAIL : ${member.email}</h1>
		  <img alt="" src="../upload/${member.fname}">
		</div>
		<div class="row">
			<a href="./memberUpdate.do">UPDATE</a>
			<a href="memberDelete.do">DELETE</a>
		</div>
	</div>


<c:import url="../../../temp/footer.jsp"/>
</body>
</html>