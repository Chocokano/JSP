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

@WebServlet("/cs/notice/view.do")
public class NoticeViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	@Override
	public void init() throws ServletException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate = req.getParameter("cate");
		req.setAttribute("cate", cate);
		
		String no = req.getParameter("no");
		
		ArticleVO NoticeView = service.NoticeView(no);
		req.setAttribute("NoticeView", NoticeView);
		
		List<Cate1VO> NoticeCate = service.SelectCsCateNotice();
		req.setAttribute("NoticeCate", NoticeCate);
		

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cs/notice/view.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
