package kr.co.kmarket.controller;

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

@WebServlet("/index.do")
public class indexController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 카테고리 불러오기
		List<Cate1VO> cate1List = service.SelectAdminProduct1();
		List<Cate2VO> cate2List = service.SelectAdminProduct2();
		
		// 상품별 데이터 불러오기
		List<ProductVO> listProduct = service.SelectProduct(0);
		List<ProductVO> listProductHit = service.SelectProduct(1);
		List<ProductVO> listProductNow = service.SelectProduct(2);
		List<ProductVO> listProductDiscount = service.SelectProduct(3);
		List<ProductVO> listProductBest = service.SelectProduct(4);
		
		req.setAttribute("listProduct", listProduct);
		req.setAttribute("listProductHit", listProductHit);
		req.setAttribute("listProductNow", listProductNow);
		req.setAttribute("listProductDiscount", listProductDiscount);
		req.setAttribute("listProductBest", listProductBest);
			
		req.setAttribute("cate1List", cate1List);			
		req.setAttribute("cate2List", cate2List);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

}
