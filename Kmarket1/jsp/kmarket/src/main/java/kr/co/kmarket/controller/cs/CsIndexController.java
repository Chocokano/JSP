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

@WebServlet("/cs/csIndex.do")
public class CsIndexController extends HttpServlet{
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
		
		// cate2 db 불러오기
		List<Cate2VO> cate2s = service.SelectCsCate2();	
		req.setAttribute("cate2s", cate2s);
		
		// Qna db 불러오기
		List<ArticleVO> CsQnaList = service.SelectArticleQna();
		req.setAttribute("CsQnaList", CsQnaList);
		
		// Notice db 불러오기
		List<ArticleVO> NoticeList = service.SelectNoticeList("10", 1);
		req.setAttribute("NoticeList", NoticeList);
		
		// Notice cate 불러오기
		List<Cate1VO> NoticeCate = service.SelectCsCateNotice();
		req.setAttribute("NoticeCate", NoticeCate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cs/csIndex.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
