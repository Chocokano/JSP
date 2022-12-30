package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CartVO;

@WebServlet("/product/CartPrice.do")
public class CartPriceController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cartNos[] = req.getParameterValues("arr");
		
		int count = 0;
		int price = 0;
		int discount = 0;
		int delivery = 0;
		int point = 0;
		int savePoint = 0;
		int total = 0;
		
		for(String cartNo : cartNos) {
			List<CartVO> carts = ProductDAO.getInstance().CartPrice(cartNo);
			
			System.out.println("cartNos : " + cartNos.length);
			System.out.println("price : " + carts.get(0).getPrice());
			System.out.println("delivery : " + carts.get(0).getDelivery());
			System.out.println("discount : " + carts.get(0).getDiscount());
			System.out.println("point : " + carts.get(0).getPoint());
			System.out.println("total : " + carts.get(0).getTotal());
			
			price += carts.get(0).getPrice();
			delivery += carts.get(0).getDelivery();
			discount += carts.get(0).getPrice()*carts.get(0).getDiscount()/100;
			point += carts.get(0).getPoint()*carts.get(0).getTotal()/100;
			total += carts.get(0).getTotal();
		}
		
		System.out.println("cartNos : " + cartNos.length);
		System.out.println("price : " + price);
		System.out.println("delivery : " + delivery);
		System.out.println("discount : " + discount);
		System.out.println("point : " + point);
		System.out.println("total : " + total);
		
		JsonObject json = new JsonObject();
		json.addProperty("price", price);
		json.addProperty("delivery", delivery);
		json.addProperty("count", cartNos.length);
		json.addProperty("discount", discount);
		json.addProperty("point", point);
		json.addProperty("total", total);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}

}