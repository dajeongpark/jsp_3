package com.dajeong.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dajeong.action.ActionForward;
import com.dajeong.page.MakePager;
import com.dajeong.page.RowNumber;

public class MemoService {
	
	private MemoDAO memoDAO;
	
	public MemoService() {
		memoDAO = new MemoDAO();
	}
	
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String [] nums = request.getParameterValues("num");
		
		int num = 0;
		MemoDAO memoDAO = new MemoDAO();
		for(String s : nums) {			
			try {
				num = Integer.parseInt(s);
				num = memoDAO.delete(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("message", num);
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionForward;
	}
	
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setContents(request.getParameter("contents"));
		memoDTO.setWriter(request.getParameter("writer"));
		
		try {
			int result = memoDAO.insert(memoDTO);
			String message = "Fail";
			if(result>0) {
				message = "Success";
			}
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionForward;
	}
	

	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		//1.curPage
		//2.kind, search
		int curPage = 1;
		try {	
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch(Exception e){
			
		}
		MakePager makePager = new MakePager(curPage, "", "");
		RowNumber rowNumber = makePager.makeRow();
		try {
			List<MemoDTO> ar = memoDAO.selectList(rowNumber);
			request.setAttribute("list", ar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = request.getPathInfo();
		//path = path.substring(0, path.lastIndexOf("."));
		// /memoList.do -> path : memoList.jsp
		// /memoMore.do -> path : memoMore.jsp
		//path = path+".jsp";
		path = path.replace(".do", ".jsp");
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/memo"+path);
		
		
		return actionForward;		
	}

}
