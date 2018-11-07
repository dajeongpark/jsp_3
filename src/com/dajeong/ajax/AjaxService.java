package com.dajeong.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dajeong.action.ActionForward;
import com.dajeong.board.BoardDTO;
import com.dajeong.member.MemberDAO;
import com.dajeong.member.MemberDTO;
import com.dajeong.notice.NoticeDAO;
import com.dajeong.page.RowNumber;
import com.dajeong.page.Search;

public class AjaxService {

	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		NoticeDAO noticeDAO = new NoticeDAO();
		RowNumber rowNumber = new RowNumber();
		rowNumber.setStartRow(1);
		rowNumber.setLastRow(10);
		rowNumber.setSearch(new Search());
		List<BoardDTO> ar = null;
		try {
			ar = noticeDAO.selectList(rowNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jr = new JSONArray();
		
		for(BoardDTO boardDTO : ar) {
			JSONObject js = new JSONObject();
			js.put("num", boardDTO.getNum());
			js.put("title", boardDTO.getTitle());
			js.put("writer", boardDTO.getWriter());
			jr.add(js);
		}
		request.setAttribute("message", jr.toJSONString());
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionForward;
	}

	public ActionForward memberInfo(HttpServletRequest request, HttpServletResponse response) {
		//id, pw, kind
		//memberDAO login 메서드 호출
		//id, name, email JSON으로 바꿔서 출력
		ActionForward actionForward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setKind(request.getParameter("kind"));
		MemberDAO memberDAO = new MemberDAO();
		try {
			memberDTO = memberDAO.login(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", memberDTO.getId());
		jsonObject.put("name", memberDTO.getName());
		jsonObject.put("email", memberDTO.getEmail());
		
		request.setAttribute("message", jsonObject.toJSONString());
		
	    actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");

		 return actionForward;
	}

	public ActionForward checkId2(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDAO mDAO = new MemberDAO();
		String id = request.getParameter("id");
		boolean result = true;
		try {
			result = mDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String message="1"; //불가능
		if(!result) {
			message="2"; //가능
		}

		String j = "{\"result\":\""+message+"\", \"m\":\"v\"}";
		JSONObject js = new JSONObject();

	      js.put("result", message);
	      //{"result":"1"}
	      js.put("m", "v");
	      //{"result":"1", "m":"v"}

		request.setAttribute("message", js.toJSONString());
		System.out.println(js.toJSONString());
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");

		return actionForward;
	}

	public ActionForward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDAO mDAO = new MemberDAO();
		String id = request.getParameter("id");
		boolean result = true;
		try {
			result = mDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String message="1"; //불가능
		if(!result) {
			message="2"; //가능
		}

		/*if(result) {
		         //불가능 1
		      }else {
		         //가능 2
		      }*/

		request.setAttribute("message", message);
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/resultAjax.jsp");

		return actionForward;
	}

}
