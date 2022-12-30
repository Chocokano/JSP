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
import kr.co.kmarket.vo.Cate2VO;

@WebServlet("/cs/qna/view.do")
public class QnaViewController extends HttpServlet{
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
		
		List<Cate2VO> cate2s = service.SelectCsCate2(cate);	
		req.setAttribute("cate2s", cate2s);
		
		ArticleVO QnaView = service.QnaView(no);
		req.setAttribute("QnaView", QnaView);

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cs/qna/view.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
