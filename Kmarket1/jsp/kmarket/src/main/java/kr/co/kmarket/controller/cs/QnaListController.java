package kr.co.kmarket.controller.cs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/cs/qna/list.do")
public class QnaListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// cate1 db 불러오기
		List<Cate1VO> cate1s = service.SelectCsCate1();	
		req.setAttribute("cate1s", cate1s);
		
		//aside cate1 정보
		String cate = req.getParameter("cate");
		req.setAttribute("cate", cate);
		
		// cate2 db 불러오기
		List<Cate2VO> cate2s = service.SelectCsCate2(cate);	
		req.setAttribute("cate2s", cate2s);

		String pg = req.getParameter("pg");
		
		
		int currentPage = service.getCurrentPage(pg); // 현재 페이지 번호 
		int total = service.selectCountTotal(cate);
		int lastPageNum = service.getLastPageNum(total);// 마지막 페이지 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum); // 페이지 그룹번호
		int pageStartNum = service.getPageStartNum(total, currentPage); // 페이지 시작번호
		int start = service.getStartNum(currentPage); // 시작 인덱스
		
		
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		List<ArticleVO> ArticleQnaList = service.SelectQnaListPage(cate, start);
		req.setAttribute("ArticleQnaList", ArticleQnaList);


		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cs/qna/list.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
