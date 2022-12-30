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

@WebServlet("/cs/qna/write.do")
public class QnaWriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//cate1
		List<Cate1VO> cate1s = service.SelectCsCate1();	
		req.setAttribute("cate1s", cate1s);
			
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/cs/qna/write.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String cate1 = req.getParameter("category1");
		String cate2 = req.getParameter("category2");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();
		
		
		ArticleVO vo =  new ArticleVO();
		vo.setCate1(cate1);
		vo.setCate2(cate2);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUid(uid);
		vo.setRegip(regip);
		
		
		service.insertQnaArticle(vo);
		
				
		// 리다이렉트
		resp.sendRedirect("/kmarket/cs/qna/list.do?cate=10");


	}
}
