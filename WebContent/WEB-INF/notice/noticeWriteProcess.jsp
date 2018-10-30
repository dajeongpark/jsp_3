<%@page import="com.dajeong.file.FileDAO"%>
<%@page import="com.dajeong.file.FileDTO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.dajeong.notice.NoticeDAO"%>
<%@page import="com.dajeong.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	String path = request.getServletContext().getRealPath("upload");
	System.out.println(path);
	int max = 1024*10;
	MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
	//path 경로에 파일 업로드 끝
	NoticeDTO ndto = new NoticeDTO();
	ndto.setTitle(multi.getParameter("title"));
	ndto.setContents(multi.getParameter("contents"));
	ndto.setWriter(multi.getParameter("writer"));
	
	//파일의 정보 
	FileDTO f1 = new FileDTO();
	f1.setFname(multi.getFilesystemName("f1"));
	f1.setOname(multi.getOriginalFileName("f1"));
	FileDTO f2 = new FileDTO();
	f2.setFname(multi.getFilesystemName("f2")); //파라미터의 이름
	f2.setOname(multi.getOriginalFileName("f2"));

/* 	File f = multi.getFile("f1");
	Enumeration e = multi.getFileNames(); //파라미터 이름들  */
	
	String s1 = request.getParameter("f1");
	
	System.out.println(s1);
	
	NoticeDAO ndao = new NoticeDAO();
	int num = ndao.getNum();
	ndto.setNum(num);
	int result = ndao.insert(ndto);
	
	f1.setNum(num);
	f2.setNum(num);
	f1.setKind("N");
	f2.setKind("N");
	
	FileDAO fileDAO = new FileDAO();
	fileDAO.insert(f1);
	fileDAO.insert(f2);
	
	
	String s = "Write Fail";
	if(result>0){
		s="Write Success";
	}
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert('<%=s%>');
	location.href="./noticeList.jsp";
</script>
</head>
<body>

</body>
</html>