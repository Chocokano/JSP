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
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/product/directOrder.do")
public class DirectOrderController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/directOrder.jsp");
		//dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String proName = req.getParameter("proName");
		String descript = req.getParameter("descript");
		String uid = req.getParameter("uid");
		String proNo = req.getParameter("proNo");
		String count = req.getParameter("count");
		String price = req.getParameter("price");
		String discount = req.getParameter("discount");
		String delivery = req.getParameter("delivery");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String thumb1 = req.getParameter("thumb1");
		String total = req.getParameter("total");
		
		CartVO cart = new CartVO();
		cart.setProName(proName);
		cart.setDescript(descript);
		cart.setUid(uid);
		cart.setProNo(proNo);
		cart.setCount(count);
		cart.setPrice(price);
		cart.setDiscount(discount);
		cart.setDelivery(delivery);
		cart.setCate1(cate1);
		cart.setCate2(cate2);
		cart.setThumb1(thumb1);
		cart.setTotal(total);
		
		List<CartVO> carts = new ArrayList<>();
		carts.add(cart);
		
		HttpSession session = req.getSession();
		session.setAttribute("carts", carts);
		
		JsonObject json = new JsonObject();
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
		}
}