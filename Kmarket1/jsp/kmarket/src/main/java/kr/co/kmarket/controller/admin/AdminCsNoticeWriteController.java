package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;

@WebServlet("/admin/cs/NoticeWrite.do")
public class AdminCsNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	@Override
	public void init() throws ServletException {
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		req.setAttribute("pg", pg);
		
		List<Cate1VO> cate1s = service.SelectNoticeCate1();	
		req.setAttribute("cate1s", cate1s);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/cs/NoticeWrite.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String cate1 = req.getParameter("category1");
		String title = req.getParameter("noticeTitle");
		String content = req.getParameter("content");
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();
		
		ArticleVO vo =  new ArticleVO();
		vo.setCate1(cate1);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUid(uid);
		vo.setRegip(regip);
	
		int total = service.insertNoticeArticle(vo);
		
				
		// 리다이렉트
		resp.sendRedirect("/kmarket/admin/cs/NoticeList.do");
		
	}	
}	
	
