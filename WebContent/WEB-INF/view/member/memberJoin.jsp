<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootstrap.jsp"/>
<script type="text/javascript">
	$(function() {
		
		$("#id").change();(function() {
			$("#idcheck").val('f');
		});
		
		$("#join").click(function() {
			var check = $("#idcheck").val();
			if(check=='s'){
				alert("OK");
			}else{
				alert("ID 중복 체크");
			}
		});
		
		$("#btn").click(function() {
			//var id=$('#id').val();
			var id = document.frm.id.value;
			window.open("./memberCheckId.do?id="+id, "CheckID","width=500, height=200, top=300, left=500");
		});		
		
	});
</script>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
	<div class="container-fluid">
		<div class="row">
			<form name="frm" action="./memberJoin.do" method="post" enctype="multipart/form-data">
			    <div class="form-group">
			      <label for="title">ID:</label>
			      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
			      <input type="button" id="btn" value="중복확인">
			    </div>
			    <div class="form-group">
			      <label for="writer">PASSWORD:</label>
			      <input type="password" class="form-control" id="pw" placeholder="Enter pw" name="pw">
			    </div>
			    <div class="form-group">
			      <label for="writer">PASSWORD:</label>
			      <input type="password" class="form-control" id="pw" placeholder="Enter pw" name="pw">
			    </div>
			    <div class="form-group">
			      <label for="title">NAME:</label>
			      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
			    </div>
			    <div class="form-group">
			      <label for="title">EMAIL:</label>
			      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
			    </div>
			    <div class="form-group">
			      <label for="title">KIND:</label>
			      <input type="text" class="form-control" id="kind" placeholder="Enter kind" name="kind">
			    </div>
			    <div class="form-group">
			      <label for="title">ClassMate:</label>
			      <input type="text" class="form-control" id="classMate" placeholder="Enter classmate" name="classMate">
			    </div>
			    
			    <div class="form-group">
			      <label for="file">FILE:</label>
			      <input type="file" class="form-control" id="file" name="f1">
			    </div>
			    <input type="button" id="join" class="btn btn-default" value="JOIN">
		 	 </form>
		</div>
	</div>


<c:import url="../../../temp/footer.jsp"/>
</body>
</html>