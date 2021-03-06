<%@page import="java.util.List"%>
<%@page import="com.dajeong.file.FileDAO"%>
<%@page import="com.dajeong.file.FileDTO"%>
<%@page import="com.dajeong.board.BoardDTO"%>
<%@page import="com.dajeong.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	int num = Integer.parseInt(request.getParameter("num"));
	
	
	NoticeDAO noticeDAO = new NoticeDAO();
	BoardDTO boardDTO = noticeDAO.selectOne(num);
	
	FileDAO fileDAO = new FileDAO();
	FileDTO fileDTO = new FileDTO();
	fileDTO.setNum(num);
	fileDTO.setKind("N");
	List<FileDTO> ar = fileDAO.selectList(fileDTO);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../temp/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../temp/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<h1>TITLE: <%=boardDTO.getTitle() %></h1>
			<h1>WRITER: <%=boardDTO.getWriter() %></h1>
			<h1>CONTENTS: <%=boardDTO.getContents() %></h1>
			<% for(FileDTO file : ar) { %>
				<h3><a href="../upload/<%=file.getFname()%>"><%=file.getOname() %></a></h3>
			<% } %>
		</div>
	</div>
	<div>
		<a href="./noticeList.jsp">LIST</a>
		<a href="./noticeUpdateForm.jsp">UPDATE</a>
		<a href="./noticeDelete.jsp">DELETE</a>
	</div>
<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>