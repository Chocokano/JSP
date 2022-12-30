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

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		List<CartVO> carts = (List<CartVO>) session.getAttribute("carts");
		
		req.setAttribute("carts", carts);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터수신
		String uid = req.getParameter("uid");
		int ordCount = Integer.parseInt(req.getParameter("ordCount"));
		int ordPrice = Integer.parseInt(req.getParameter("ordPrice"));
		String ordDiscount = req.getParameter("ordDiscount");
		String ordDelivery = req.getParameter("ordDelivery");
		String savePoint = req.getParameter("savePoint");
		int usedPoint = Integer.parseInt(req.getParameter("usedPoint"));
		int ordTotPrice = Integer.parseInt(req.getParameter("ordTotPrice"));
		String recipName = req.getParameter("recipName");
		String recipHp = req.getParameter("recipHp");
		String recipZip = req.getParameter("recipZip");
		String recipAddr1 = req.getParameter("recipAddr1");
		String recipAddr2 = req.getParameter("recipAddr2");
		String ordPayment = req.getParameter("ordPayment");
		
		ordTotPrice = ordPrice - usedPoint;
		
		OrderVO ov = new OrderVO();
		ov.setUid(uid);
		ov.setOrdCount(ordCount);
		ov.setOrdPrice(ordPrice);
		ov.setOrdDiscount(ordDiscount);
		ov.setOrdDelivery(ordDelivery);
		ov.setSavePoint(savePoint);
		ov.setUsedPoint(usedPoint);
		ov.setOrdTotPrice(ordTotPrice);
		ov.setRecipName(recipName);
		ov.setRecipHp(recipHp);
		ov.setRecipZip(recipZip);
		ov.setRecipAddr1(recipAddr1);
		ov.setRecipAddr2(recipAddr2);
		ov.setOrdPayment(ordPayment);
		
		int result = ProductDAO.getInstance().InsertProductOrder(ov);
		System.out.println("result : " + result);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
}