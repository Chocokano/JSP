package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.TermsVO;

@WebServlet("/member/signup.do")
public class SignupController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TermsVO tv = service.SelectTerms();
		req.setAttribute("tv", tv);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/member/signup.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}