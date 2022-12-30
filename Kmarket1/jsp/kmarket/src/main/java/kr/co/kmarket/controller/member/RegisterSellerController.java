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

@WebServlet("/member/registerSeller.do")
public class RegisterSellerController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/member/registerSeller.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String company = req.getParameter("company");
		String ceo = req.getParameter("ceo");
		String corp = req.getParameter("corp_reg");
		String online = req.getParameter("online_reg");
		String tel = req.getParameter("tel");
		String fax = req.getParameter("fax");
		String email = req.getParameter("email");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String manager = req.getParameter("name");
		String managerHp = req.getParameter("hp");
		String regip = req.getRemoteAddr();
		
		MemberVO vo = new MemberVO();
		vo.setUid(uid);
		vo.setPass(pass);
		vo.setCompany(company);
		vo.setCeo(ceo);
		vo.setBizRegNum(corp);
		vo.setComRegNum(online);
		vo.setTel(tel);
		vo.setFax(fax);
		vo.setEmail(email);
		vo.setZip(zip);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setManager(manager);
		vo.setManagerHp(managerHp);
		vo.setRegip(regip);
		
		//데이터 처리
		service.InsertMemberSeller(vo);
		
		// 리다이렉트
		resp.sendRedirect("/kmarket/member/login.do");
	}
}