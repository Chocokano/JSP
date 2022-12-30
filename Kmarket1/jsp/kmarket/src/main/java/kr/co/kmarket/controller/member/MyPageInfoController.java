package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/member/mypageinfo.do")
public class MyPageInfoController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/member/mypageinfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		MemberVO mv = new MemberVO();
		mv.setUid(uid);
		mv.setName(name);
		mv.setGender(Integer.parseInt(gender));
		mv.setEmail(email);
		mv.setHp(hp);
		mv.setZip(zip);
		mv.setAddr1(addr1);
		mv.setAddr2(addr2);
		
		MemberDAO.getInstance().UpdateMemberInfo(mv);
		
		//멤버값 변경
		MemberVO newInfo = MemberDAO.getInstance().SelectMemberForChangeInfo(uid);
		HttpSession session = req.getSession();
		session.setAttribute("sessUser", newInfo);
		
		resp.sendRedirect("/kmarket/index.do");
	}
}
