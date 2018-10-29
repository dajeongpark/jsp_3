
<%@page import="java.util.ArrayList"%>
<%@page import="com.dajeong.notice.NoticeDTO"%>
<%@page import="com.dajeong.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../temp/bootstrap.jsp"></jsp:include>

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

<jsp:include page="../temp/header.jsp"></jsp:include>
<%-- <%
	MemberDTO mdto = (MemberDTO)session.getAttribute("member");
%> --%>
<div class="container-fluid">
	<div class="row">
	  <form action="./noticeWriteProcess.jsp" method="post" enctype="multipart/form-data"> 
	    <div class="form-group">
	      <label for="title">TITLE:</label>
	      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
	    </div>
	    <div class="form-group">
	      <label for="writer">WRITER:</label>
	      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer">
	    </div>
	    <div class="form-group">
	      <label for="contents">CONTENTS:</label>
	      <textarea rows="10" cols="" class="form-control" name="contents"></textarea>
	    </div>
	    <div class="form-group">
	      <label for="file">FILE:</label>
	      <input type="file" class="form-control" id="file" name="f1">
	    </div>
	    <div class="form-group">
	      <label for="file">FILE:</label>
	      <input type="file" class="form-control" id="file" name="f2">
	    </div>
	    <button type="submit" class="btn btn-default">WRITER</button>
 	 </form>
	</div>
</div>

<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>