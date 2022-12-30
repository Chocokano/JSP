package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.CartVO;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.ProductVO;


@WebServlet("/product/cart.do")
public class CartController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Cate1VO> cate1List = service.SelectAdminProduct1();
		req.setAttribute("cate1List", cate1List);
		
		List<Cate2VO> cate2List = service.SelectAdminProduct2();
		req.setAttribute("cate2List", cate2List);
		
		String uid = req.getParameter("uid");
		
		List<CartVO> carts = ProductDAO.getInstance().SelectProductCarts(uid);
		req.setAttribute("carts", carts);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/cart.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cartNos[] = req.getParameterValues("arr");
		
		List<CartVO> carts = new ArrayList<>();
		
		for(String cartNo : cartNos) {
			CartVO vo = ProductDAO.getInstance().SelectProductOrder(cartNo);
			carts.add(vo);
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("carts", carts);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", carts.size());
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
}
