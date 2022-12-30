package kr.co.kmarket.dao;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.Sql;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.ProductVO;

public class ProductDAO extends DBHelper{
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	private ProductDAO() {}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ProductVO SelectProductView(String proNo) {
		
		ProductVO pv = null;
		
		try {
			logger.info("SelectProductView start...");
			
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_VIEW);
			psmt.setString(1, proNo);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				pv = new ProductVO();
				
				pv.setProNo(rs.getInt(1));
				pv.setCate1(rs.getInt(2));
				pv.setCate2(rs.getInt(3));
				pv.setProName(rs.getString(4));
				pv.setDescript(rs.getString(5));
				pv.setCompany(rs.getString(6));
				pv.setSeller(rs.getString(7));
				pv.setPrice(rs.getInt(8));
				pv.setDiscount(rs.getInt(9));
				pv.setPoint(rs.getInt(10));
				pv.setStock(rs.getInt(11));
				pv.setSold(rs.getInt(12));
				pv.setDelivery(rs.getInt(13));
				pv.setHit(rs.getInt(14));
				pv.setScore(rs.getInt(15));
				pv.setReview(rs.getInt(16));
				pv.setThumb1(rs.getString(17));
				pv.setThumb2(rs.getString(18));
				pv.setThumb3(rs.getString(19));
				pv.setDetail(rs.getString(20));
				pv.setStatus(rs.getString(21));
				pv.setDuty(rs.getString(22));
				pv.setRecipt(rs.getString(23));
				pv.setBizType(rs.getString(24));
				pv.setOrigin(rs.getString(25));
				pv.setIp(rs.getString(26));
				pv.setRdate(rs.getString(27));
				pv.setEtc1(rs.getInt(28));
				pv.setEtc2(rs.getInt(29));
				pv.setEtc3(rs.getString(30));
				pv.setEtc4(rs.getString(31));
				pv.setEtc5(rs.getString(32));
				pv.setC1Name(rs.getString(33));
				pv.setC2Name(rs.getString(34));
						
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("SelectProductView error!!!");
		}	
		return pv;
	}
	
	public int InsertProductCart(CartVO cart) {
		
		int result = 0;
		
		try {
			logger.info("Insert Product Cart Start...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Insert_Product_Cart);
			psmt.setString(1, cart.getUid());
			psmt.setInt(2, cart.getProNo());
			psmt.setInt(3, cart.getCount());
			psmt.setInt(4, cart.getPrice());
			psmt.setInt(5, cart.getDiscount());
			psmt.setInt(6, cart.getDelivery());
			psmt.setInt(7, cart.getTotal());
			result = psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	//상품 존재 여부 확인하기
	public int SelectProductCart(String uid, String proNo) {
		
		int check = 0;
		
		try {
			logger.info("Select Procut Cart Start..");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Select_Product_Cart);
			psmt.setString(1, uid);
			psmt.setString(2, proNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				check = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}return check;
	}
	
	//장바구니 출력하기
	public List<CartVO> SelectProductCarts(String uid) {
		
		List<CartVO> carts = new ArrayList<>();
		
		try {
			logger.info("Select Product Carts Start...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Select_Product_Carts);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setProName(rs.getString(1));
				cart.setDescript(rs.getString(2));
				cart.setCartNo(rs.getInt(3));
				cart.setUid(rs.getString(4));
				cart.setProNo(rs.getInt(5));
				cart.setCount(rs.getInt(6));
				cart.setPrice(rs.getInt(7));
				cart.setDiscount(rs.getInt(8));
				cart.setPoint(rs.getInt(9));
				cart.setDelivery(rs.getInt(10));
				cart.setTotal(rs.getInt(11));
				cart.setRdate(rs.getString(12));
				cart.setThumb1(rs.getString(13));
				cart.setCate1(rs.getInt(14));
				cart.setCate2(rs.getInt(15));
				carts.add(cart);
			}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return carts;
	}
	
	//중복 상품 업데이트
	public int UpdateProductCartCount(int count, String uid, String proNo) {
		
		int result = 0;
		
		try {
			logger.info("Update Product Cart Count");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Update_Product_Cart_Count);
			psmt.setInt(1, count);
			psmt.setString(2, uid);
			psmt.setString(3, proNo);
			result = psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int DeleteCart(String cartNo) {
			
			int result = 0;
			
			try {
				logger.info("Delete Cart Start...");
				conn = getConnection();
				psmt = conn.prepareStatement(Sql.Delete_Cart);
				psmt.setString(1, cartNo);
				result = psmt.executeUpdate();
				close();
				
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
			
			return result;
	}
	
	public List<CartVO> CartPrice(String cartNo) {
		
		List<CartVO> carts = new ArrayList<>(); 
		
		try {
			logger.info("Cart Price Start...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.CART_PRICE);
			psmt.setString(1, cartNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				CartVO cart = new CartVO();
				cart.setCartNo(rs.getInt(1));
				cart.setUid(rs.getString(2));
				cart.setProNo(rs.getInt(3));
				cart.setCount(rs.getInt(4));
				cart.setPrice(rs.getInt(5));
				cart.setDiscount(rs.getInt(6));
				cart.setPoint(rs.getInt(7));
				cart.setDelivery(rs.getInt(8));
				cart.setTotal(rs.getInt(9));
				carts.add(cart);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return carts;
	}
	
	public CartVO SelectProductOrder(String cartNo) {
		CartVO cart = null;
		try {
			logger.info("Select Product Order Start...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Select_Product_Order);
			psmt.setString(1, cartNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				cart = new CartVO();
				cart.setCartNo(rs.getInt(1));
				cart.setUid(rs.getString(2));
				cart.setProNo(rs.getInt(3));
				cart.setCount(rs.getInt(4));
				cart.setPrice(rs.getInt(5));
				cart.setDiscount(rs.getInt(6));
				cart.setPoint(rs.getInt(7));
				cart.setDelivery(rs.getInt(8));
				cart.setTotal(rs.getInt(9));
				cart.setProName(rs.getString(11));
				cart.setDescript(rs.getString(12));
				cart.setThumb1(rs.getString(13));
				cart.setCate1(rs.getInt(14));
				cart.setCate2(rs.getInt(15));
				
			}
			close();	
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return cart;
	}
	
	public int InsertProductOrder(OrderVO vo) {
		
		int result = 0;
		
		try {
			logger.info("Insert Product Order Start..");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.Insert_Product_Order);
			psmt.setString(1, vo.getUid());
			psmt.setInt(2, vo.getOrdCount());
			psmt.setInt(3, vo.getOrdPrice());
			psmt.setInt(4, vo.getOrdDiscount());
			psmt.setInt(5, vo.getOrdDelivery());
			psmt.setInt(6, vo.getSavePoint());
			psmt.setInt(7, vo.getUsedPoint());
			psmt.setInt(8, vo.getOrdTotPrice());
			psmt.setString(9, vo.getRecipName());
			psmt.setString(10, vo.getRecipHp());
			psmt.setString(11, vo.getRecipZip());
			psmt.setString(12, vo.getRecipAddr1());
			psmt.setString(13, vo.getRecipAddr2());
			psmt.setInt(14, vo.getOrdPayment());
			result = psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void Insert_Product_OrderItem() {
		
	}
}

