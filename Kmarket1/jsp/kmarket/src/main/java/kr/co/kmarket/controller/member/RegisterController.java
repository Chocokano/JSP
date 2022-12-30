package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/member/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신
		String uid = req.getParameter("uid"); 
		String pass1 = req.getParameter("pass1"); 
		String pass2 = req.getParameter("pass2"); 
		String name = req.getParameter("name"); 
		String gender = req.getParameter("gender"); 
		String email = req.getParameter("email"); 
		String hp = req.getParameter("hp"); 
		String zip = req.getParameter("zip"); 
		String addr1 = req.getParameter("addr1"); 
		String addr2 = req.getParameter("addr2"); 
		String regip = req.getRemoteAddr();
		
		//VO 데이터설정
		MemberVO mv = new MemberVO();
		mv.setUid(uid);
		mv.setPass(pass1);
		mv.setName(name);
		mv.setGender(Integer.parseInt(gender));
		mv.setEmail(email);
		mv.setHp(hp);
		mv.setZip(zip);
		mv.setAddr1(addr1);
		mv.setAddr2(addr2);
		mv.setRegip(regip);
		
		//데이터베이스 처리
		service.InsertMember(mv);
		
		//리다이렉트
		resp.sendRedirect("/kmarket/member/login.do");
	}
}
