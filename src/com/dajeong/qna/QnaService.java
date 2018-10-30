package com.dajeong.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dajeong.action.ActionForward;
import com.dajeong.board.BoardDTO;
import com.dajeong.page.MakePager;
import com.dajeong.page.Pager;
import com.dajeong.page.RowNumber;

public class QnaService {
	
	private QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO = new QnaDAO();
	}
	
	//select
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(false);
		actionForward.setPath("./qnaList.do");
		BoardDTO boardDTO = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			boardDTO = qnaDAO.selectOne(num);
			
			request.setAttribute("dto", boardDTO);
			
			actionForward.setPath("../WEB-INF/qna/qnaSelectOne.jsp");
			actionForward.setCheck(true);

		}catch (Exception e) {
			
		}
		
		if(boardDTO==null){
			actionForward.setCheck(false);
			actionForward.setPath("./qnaList.do");
		}
		return actionForward;
	}
	
	//list
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward actionForward = new ActionForward();
		int curPage = 1;
		try {
			curPage = Integer.getInteger(request.getParameter("curPage"));
			
		}catch (Exception e) {

		}		
		
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		MakePager mk = new MakePager(curPage, search, kind);
		
		RowNumber rowNumber = mk.makeRow();
		List<BoardDTO> ar;
		try {
			ar = qnaDAO.selectList(rowNumber);
			int totalCount = qnaDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePager(totalCount);
			
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			
			actionForward.setPath("../WEB-INF/qna/qnaList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		actionForward.setCheck(true);
		return actionForward;
		
	}

}
