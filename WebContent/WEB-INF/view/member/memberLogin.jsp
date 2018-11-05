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

</script>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
	<div class="container-fluid">
		<div class="row">
			<h3>${message}</h3>
		</div>
		<div class="row">
			<form name="frm" action="./memberLogin.do" method="post" >
			<input type="hidden" value="f" name="idcheck" id="idcheck">
			    <div class="form-group">
			      <label for="id">ID:</label>
			      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
			    </div>
				<div class="form-group">
			      <label for="pw">PASSWORD:</label>
			      <input type="password" class="form-control" id="pw" placeholder="Enter pw" name="pw">
			    </div>
			    <div class="form-group">
			      <label for="kind">KIND:</label>
			      <input type="text" class="form-control" id="kind" placeholder="Enter kind" name="kind">
			    </div>

			    
			    <button type="submit" class="btn btn-default">LOGIN</button>
		 	 </form>
		</div>
	</div>


<c:import url="../../../temp/footer.jsp"/>
</body>
</html>