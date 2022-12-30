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
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;



@WebServlet("/admin/cs/NoticeList.do")
public class AdminCsNoticeListController extends HttpServlet {
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
		
		int currentPage = service.getCurrentPage(pg); // 현재 페이지 번호 
		int total = service.selectCountTotalNotice();
		int lastPageNum = service.getLastPageNum(total);// 마지막 페이지 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum); // 페이지 그룹번호
		int pageStartNum = service.getPageStartNum(total, currentPage); // 페이지 시작번호
		int start = service.getStartNum(currentPage); // 시작 인덱스
		
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		List<ArticleVO> noticeList = service.SelectNotice(start);
		req.setAttribute("noticeList", noticeList);		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/cs/NoticeList.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}	
}	
	
