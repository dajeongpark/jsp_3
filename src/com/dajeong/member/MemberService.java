package com.dajeong.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.modeler.FeatureInfo;

import com.dajeong.action.ActionForward;
import com.dajeong.board.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService {

	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	
	}
	
	public ActionForward checkid(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		String id = request.getParameter("id");
		boolean check = true;
		String result = "1"; //사용가능, no = 사용불가능
		try {
			check = memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(check) {
			result = "2";
		}
		request.setAttribute("result", result);
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/member/memberCheckId.jsp");		
		
		return actionForward;
	}
	
	//update
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			//post
			int max = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			String message = "Update Fail";
			
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
				MemberDTO memberDTO = new MemberDTO();
				MemberDTO sessionDTO =(MemberDTO)request.getSession().getAttribute("member");
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				memberDTO.setFname(sessionDTO.getFname());
				memberDTO.setOname(sessionDTO.getOname());
				memberDTO.setKind(sessionDTO.getKind());
				memberDTO.setClassMate(sessionDTO.getClassMate());
				file = multi.getFile("f1");
				if(file!=null) {
					file = new File(path, memberDTO.getFname());
					file.delete();
					memberDTO.setFname(multi.getFilesystemName("f1"));
					memberDTO.setOname(multi.getOriginalFileName("f1"));
				}
				
				int result = memberDAO.update(memberDTO);
				if(result>0) {
					request.getSession().setAttribute("member", memberDTO);
					message = "Update Success";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("message", message);
			request.setAttribute("path", "./memberMypage.do");
			
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
			
		}else {
			//get
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberUpdate.jsp");
		}
		
		return actionForward;
	}
	
	//delete
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDTO memberDTO = null;
		HttpSession session = request.getSession();
		memberDTO = (MemberDTO)session.getAttribute("member");
		String message="Delete Fail";
		try {
			int result = memberDAO.delete(memberDTO);
			if(result>0) {
				message = "Delete Success";
				String path = session.getServletContext().getRealPath("upload");
				File file = new File(path, memberDTO.getFname());
				file.delete();
				session.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.setAttribute("path", "../index.jsp");
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/result.jsp");
		
		return actionForward;
	}
	
	//MyPage
	public ActionForward myPage(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/member/memberMypage.jsp");		
		
		return actionForward;
	}
	
	//logout
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		HttpSession session = request.getSession();
		session.invalidate();
		
		actionForward.setCheck(false);
		actionForward.setPath("../index.jsp");
		
		return actionForward;		
	}
	
	//login
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			memberDTO.setKind(request.getParameter("kind"));
			try {
				memberDTO = memberDAO.login(memberDTO);
			} catch (Exception e) {
				memberDTO = null;
				e.printStackTrace();
			}
			if(memberDTO!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO);
				actionForward.setCheck(false);
				actionForward.setPath("../index.jsp");
			}else {
				request.setAttribute("message", "Login Fail");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/member/memberLogin.jsp");
			}
		}else {
			//get 
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}
		return actionForward;
	}
	
	//join
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			int max =  1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			System.out.println(path);
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			//파일저장
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
				//Member 데이터 
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				memberDTO.setKind(multi.getParameter("kind"));
				memberDTO.setClassMate(multi.getParameter("classMate"));
				memberDTO.setFname(multi.getFilesystemName("f1"));
				memberDTO.setOname(multi.getOriginalFileName("f1"));
				//파일의 정보를 DTO에 추가 
				//
				int result = memberDAO.join(memberDTO);
				if(result>0) {
					request.setAttribute("message", "Join Success");
					request.setAttribute("path", "../index.jsp");
				}else {
					request.setAttribute("message", "Join Fail");
					request.setAttribute("path", "./memberJoin.do");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
		}else {
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}

		return actionForward;
	}

}
