<%@page import="com.dajeong.page.Pager"%>
<%@page import="com.dajeong.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.dajeong.page.MakePager"%>
<%@page import="com.dajeong.notice.NoticeDAO"%>
<%@page import="com.dajeong.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	int curPage = 1;
	try {
		
	}catch(Exception e){
		curPage = Integer.parseInt(request.getParameter("curPage"));
	}
	
	String kind = request.getParameter("kind");
	if(kind==null || kind.equals("")){
		kind = "title";
	}
	
	String search = request.getParameter("search");
	if(search==null){
		search = "";	
	}
	
	BoardDAO boardDAO = new NoticeDAO();
	MakePager mk = new MakePager(curPage, search, kind);
	List<BoardDTO> ar = boardDAO.selectList(mk.makeRow());
	
	int totalCount = boardDAO.getCount(kind, search);
	//paging
	Pager pager = mk.makePager(totalCount);
	
	request.setAttribute("list", ar);
	request.setAttribute("board", "notice");
	request.setAttribute("pager", pager);
	
	RequestDispatcher view = request.getRequestDispatcher("../board/boardList.jsp");
	view.forward(request, response);
%>
