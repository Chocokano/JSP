package kr.co.kmarket.controller.product;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String cate = req.getParameter("cate");
		String pg = req.getParameter("pg");
		
		// aside-카테고리 목록 불러오기
		List<Cate1VO> cate1List = service.SelectAdminProduct1();
		List<Cate2VO> cate2List = service.SelectAdminProduct2();
		
		// cate name 불러오기
		List<Cate1VO> cate1Name = service.SelectCate1Name(cate1);
		List<Cate2VO> cate2Name = service.SelectCate2Name(cate1, cate2);
		
		// list 페이지 번호
		int currentPage = service.getCurrentPage(pg); // 현재 페이지 번호 
		int total = service.selectCountTotal(cate1, cate2);
		int lastPageNum = service.getLastPageNum(total);// 마지막 페이지 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum); // 페이지 그룹번호
		int pageStartNum = service.getPageStartNum(total, currentPage); // 페이지 시작번호
		int start = service.getStartNum(currentPage); // 시작 인덱스
		
		
		//list 불러오기
		List<ProductVO> ListProduct = service.SelectProductList(cate1, cate2, cate, start);
	
		req.setAttribute("cate1List", cate1List);
		req.setAttribute("cate2List", cate2List);				
		req.setAttribute("cate1Name", cate1Name);
		req.setAttribute("cate2Name", cate2Name);
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		req.setAttribute("cate", cate);
		req.setAttribute("ListProduct", ListProduct);
		
		req.setAttribute("lastPageNum", lastPageNum);		
		req.setAttribute("currentPage", currentPage);		
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}