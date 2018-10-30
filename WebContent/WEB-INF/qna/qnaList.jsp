<%@page import="com.dajeong.page.Pager"%>
<%@page import="com.dajeong.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dajeong.qna.QnaDAO"%>
<%@page import="com.dajeong.qna.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
	List<BoardDTO> ar = (List<BoardDTO>)request.getAttribute("list");
	Pager pager = (Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../temp/bootstrap.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">
	
<%@ include file="../../temp/header.jsp" %>

<div class="container-fluid">
<h1>QNA LIST</h1>
	<div class="row">
	  <div>
		  <form class="form-inline" action="./qnaList.do">
		    <div class="form-group">
		          <select class="form-control" id="sel1" name="kind">
			        <option>Title</option>
			        <option>Contents</option>
			        <option>Writer</option>
			      </select>
		      <input type="text" class="form-control" id="search" placeholder="Enter search" name="search">
		    </div>

		    <button type="submit" class="btn btn-default">Search</button>
		  </form>
	  </div>
		 <table class="table table-hover">
			<tr>
				<td>NO</td>
				<td>SUBJECT</td>
				<td>WRITER</td>
				<td>DATE</td>
				<td>HIT</td>			
			</tr>
				<% for(BoardDTO boardDTO : ar) { %>
					<tr>
						<td><%= boardDTO.getNum() %></td>
						<td>
							<a href="./qnaSelectOne.jsp?num=<%= boardDTO.getNum() %>">
							<%= boardDTO.getTitle() %>
							</a>
						</td>
						<td><%= boardDTO.getWriter() %></td>
						<td><%= boardDTO.getReg_date() %></td>
						<td><%= boardDTO.getHit() %></td>
					</tr>
				<% } %>
		</table>
	 </div>
</div>
<!-- page 번호 -->
	<div class="container">
   		   <ul class="pagination">
   		   <li><a href="./qnaList.do?curPage=<%=1%>"><span class="	glyphicon glyphicon-backward"></span></a></li>
   		   	<%if(pager.getCurBlock()>1){ %>
   		   	<li><a href="./qnaList.do?curPage=<%=pager.getStartNum()-1%>"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
   		   	<%} %>
   		   	
		    <% for(int i=pager.getStartNum();i<=pager.getLastNum();i++){ %>
		    	<li><a href="./qnaList.do?curPage=<%=i%>"><%=i%></a></li>
		    <% } %>
		    <%if(pager.getCurBlock()!=pager.getTotlaBlock()){ %>
		    <li><a href="./qnaList.do?curPage=<%=pager.getLastNum()+1%>"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
		    <% } %>
		    <li><a href="./qnaList.do?curPage=<%=pager.getTotalPage()%>"><span class="glyphicon glyphicon-forward"></span></a></li>
		  </ul>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<a href="./qnaWriteForm.jsp" class="btn btn-warning">WRITE</a>
			</div>
		</div>
	</div>
<jsp:include page="../../temp/footer.jsp"></jsp:include>
</body>
</html>