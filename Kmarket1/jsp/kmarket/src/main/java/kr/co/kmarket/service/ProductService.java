package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.ProductVO;

public enum ProductService {
	
	INSTANCE;
	private ProductDAO dao = ProductDAO.getInstance();
	
	public ProductVO SelectProductView(String proNo) {
		return dao.SelectProductView(proNo); 
	}
	public List<CartVO> CartPrice(String cartNo) {
		return dao.CartPrice(cartNo);
	}
	
}
