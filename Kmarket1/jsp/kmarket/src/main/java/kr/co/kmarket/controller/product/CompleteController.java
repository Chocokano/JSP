package kr.co.kmarket.controller.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.OrderItemVO;

@WebServlet("/product/complete.do")
public class CompleteController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/complete.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String orderNos[] = req.getParameterValues("arr");
		
		List<OrderItemVO> oiv = new ArrayList<>();
		
		for(String orderNo : orderNos) {
			ProductDAO.getInstance().Insert_Product_OrderItem();
			
		}
		
		
	}
}