package com.dajeong.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dajeong.action.ActionForward;
import com.dajeong.board.BoardDTO;
import com.dajeong.file.FileDAO;
import com.dajeong.file.FileDTO;
import com.dajeong.page.MakePager;
import com.dajeong.page.Pager;
import com.dajeong.page.RowNumber;

public class NoticeService {
	
	private NoticeDAO noticeDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
	}
	
	//selectList
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		int curPage = 1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch(Exception e) {
			
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();
		try {
			List<BoardDTO> ar = noticeDAO.selectList(rowNumber);
			int totalCount = noticeDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePager(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			actionForward.setPath("../WEB-INF/notice/noticeList.jsp");
		} catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "../index.jsp");
			actionForward.setPath("../WEB-INF/common/result.jsp");
			e.printStackTrace();
		}
		
		actionForward.setCheck(true);
		
		return actionForward;
	}
	
	//selectOne
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		BoardDTO boardDTO = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			boardDTO = noticeDAO.selectOne(num);
			FileDAO fileDAO = new FileDAO();
			FileDTO fileDTO = new FileDTO();
			fileDTO.setNum(num);
			fileDTO.setKind("N");
			List<FileDTO> ar = fileDAO.selectList(fileDTO);
			request.setAttribute("dto", boardDTO);
			request.setAttribute("files", ar);
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/notice/noticeSelectOne.jsp");
		} catch (Exception e) {
			actionForward.setCheck(false);
			actionForward.setPath("./noticeList.do");
			e.printStackTrace();
		}
		
		if(boardDTO==null) {
			actionForward.setCheck(false);
			actionForward.setPath("./noticeList.do");
		}
		return actionForward;
	}

}
