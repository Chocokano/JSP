package kr.co.kmarket.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.Sql;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.ProductVO;

public class AdminDAO extends DBHelper{
	
	private static AdminDAO instance = new AdminDAO();
	
	public static AdminDAO getInstance() {
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	//admin category1
	public List<Cate1VO> SelectAdminProduct1() {
		
		List<Cate1VO> cate1s = new ArrayList<>();
		
		try {
			logger.info("select cate1 start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE1);
			
			while(rs.next()) {
				Cate1VO cate1 = new Cate1VO();
				cate1.setCate1(rs.getInt(1));
				cate1.setC1Name(rs.getString(2));
				
				cate1s.add(cate1);
			}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("cate1 error..");
		}
		return cate1s;
	}
	
	// select cate1Name
	public List<Cate1VO> SelectCate1Name(String cate1) {
			List<Cate1VO> cate1s = new ArrayList<>();
			try {
				logger.info("select cate1Name start...");
				
				conn = getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_CATE1_NAME);
				psmt.setString(1, cate1);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					Cate1VO c1 = new Cate1VO();
					c1.setCate1(rs.getInt(1));
					c1.setC1Name(rs.getString(2));
					cate1s.add(c1);
				}
				close();
			}catch(Exception e) {
				logger.error("cate1 Name error..");
			}
			return cate1s;
		}
	// select cate2Name
	public List<Cate2VO> SelectCate2Name(String cate1 ,String cate2) {
			List<Cate2VO> cate2s = new ArrayList<>();
			try {
				logger.info("select cate2Name start...");
				
				conn = getConnection();
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_CATE2_NAME);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					Cate2VO c2 = new Cate2VO();
					c2.setCate1(rs.getInt(1));
					c2.setCate2(rs.getInt(2));
					c2.setC2Name(rs.getString(3));
					cate2s.add(c2);
				}
				close();
			}catch(Exception e) {
				logger.error("cate2 Name error..");
			}
			return cate2s;
		}
	// view select product
	
	public List<ProductVO> SelectProductList(String cate1, String cate2, String cate, int start) {
		List<ProductVO> productView = new ArrayList<>();
		try {
			logger.info("select productView start...");
			
			conn = getConnection();
			PreparedStatement psmt = null;
			if(cate.equals("list_1")) { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_SOLD);}
			else if(cate.equals("list_2")) { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_PRICE_A);}
			else if(cate.equals("list_3")) { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_PRICE_D);}
			else if(cate.equals("list_4")) { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_SCORE);}
			else if(cate.equals("list_5")) { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_REVIEW);}
			else { psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_LIST_CATE_RDATE);}
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setInt(3, start);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO pv = new ProductVO();
				
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
		
				productView.add(pv);
			}
			close();
		}catch(Exception e) {
			logger.error("productView  error..");
		}
		return productView;
	}
	//admin category2
	public List<Cate2VO> SelectAdminProduct2(String cate1) {
		
		List<Cate2VO> cate2s = new ArrayList<>();
		
		try {
			logger.info("select cate2 start...");
			
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_CATE2);
			psmt.setString(1, cate1);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Cate2VO cate2 = new Cate2VO();
				cate2.setCate1(rs.getInt(1));
				cate2.setCate2(rs.getInt(2));
				cate2.setC2Name(rs.getString(3));
				
				cate2s.add(cate2);
			}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return cate2s;
	}
	
	//admin category2 aside
	public List<Cate2VO> SelectAdminProduct2() {
		
		List<Cate2VO> cate2s = new ArrayList<>();
		
		try {
			logger.info("select cate2 start...");
			
			conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs =stmt.executeQuery(Sql.SELECT_CATE2_);
			
			while(rs.next()) {
				Cate2VO cate2 = new Cate2VO();
				cate2.setCate1(rs.getInt(1));
				cate2.setCate2(rs.getInt(2));
				cate2.setC2Name(rs.getString(3));
				cate2s.add(cate2);
			}
			close();
			
		}catch(Exception e) {
			logger.error("cate2 select error..");
		}
		return cate2s;
	}
	
	//admin insert product
	public void INSERT_ADMIN_PRODUCT(ProductVO pv) {
		
		try {
			logger.info("insert admin product start...");
			
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ADMIN_PRODUCT);
			psmt.setInt(1, pv.getCate1());
			psmt.setInt(2, pv.getCate2());
			psmt.setString(3, pv.getProName());
			psmt.setString(4, pv.getDescript());
			psmt.setString(5, pv.getCompany());
			psmt.setInt(6, pv.getPrice());
			psmt.setInt(7, pv.getDiscount());
			psmt.setInt(8, pv.getPoint());
			psmt.setInt(9, pv.getStock());
			psmt.setInt(10, pv.getDelivery());
			psmt.setString(11, pv.getThumb1());
			psmt.setString(12, pv.getThumb2());
			psmt.setString(13, pv.getThumb3());
			psmt.setString(14, pv.getDetail());
			psmt.setString(15, pv.getStatus());
			psmt.setString(16, pv.getDuty());
			psmt.setString(17, pv.getRecipt());
			psmt.setString(18, pv.getOrigin());
			psmt.setString(19, pv.getIp());
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
	
		}
	}
	
	// 상품 불러오기
	public List<ProductVO> SelectProduct(int code) {
		
		List<ProductVO> listProduct = new ArrayList<>();
		
		try {
			logger.info("SelectProduct start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			if(code == 0) {rs =stmt.executeQuery(Sql.SELECT_PRODUCT);}
			if(code == 1) {rs =stmt.executeQuery(Sql.SELECT_PRODUCT_HIT);}
			if(code == 2) {rs =stmt.executeQuery(Sql.SELECT_PRODUCT_NOW);}
			if(code == 3) {rs =stmt.executeQuery(Sql.SELECT_PRODUCT_DISCOUNT);}
			if(code == 4) {rs =stmt.executeQuery(Sql.SELECT_PRODUCT_BEST);}
			
			while(rs.next()) {
				ProductVO pv = new ProductVO();
				
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
				
				listProduct.add(pv);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}	
		return listProduct;
	}
	
	
	public List<ProductVO> SelectProductAdmin(String seller, int start) {
		
		List<ProductVO> listProduct = new ArrayList<>();
		String master="master";
		try {
			logger.info("SELECT_PRODUCT_ADMIN start...");
			
			conn = getConnection();
			PreparedStatement psmt = null;
			ResultSet rs = null;
			if(master.equals(seller)) {
				psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_ADMIN2);
				psmt.setInt(1,start);	
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_ADMIN);
				psmt.setString(1,seller);
				psmt.setInt(2,start);

			}
			rs =psmt.executeQuery();	
			while(rs.next()) {
				ProductVO pv = new ProductVO();
				
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
				
				listProduct.add(pv);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}	
		return listProduct;
	}
	
	
	public List<Cate1VO> SelectCate1() {
		List<Cate1VO> cateList = new ArrayList<>();
		
		try {
			
			logger.info("SelectProduct start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE1);
			
			close();
			}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return cateList;	
	}
	
	public int selectCountTotal(String cate1, String cate2) {
		int total =0;	
		try{
			logger.info("selectCountTotal start...");
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_COUNT_TOTAL);
			psmt.setString(1,cate1);
			psmt.setString(2,cate2);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public int selectCountTotal(String seller) {
		int total =0;
		String master="master";
		
		try{
			logger.info("selectCountTotal start...");
			conn = getConnection();
			PreparedStatement psmt = null;
			Statement stmt =null;
			ResultSet rs = null;
			
			if(master.equals(seller)) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(Sql.SELECT_PRODUCT_COUNT_TOTAL_ADMIN2);
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_PRODUCT_COUNT_TOTAL_ADMIN);
				psmt.setString(1,seller);
				rs = psmt.executeQuery();
			}
	
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	
	public List<Cate1VO> SelectNoticeCate1() {
		
		List<Cate1VO> cate1s = new ArrayList<>();
		
		try {
			logger.info("select NOTICE cate1 start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE1_NOTICE);
		
			
			while(rs.next()) {
				Cate1VO cate1 = new Cate1VO();
				cate1.setCate1(rs.getInt(1));
				cate1.setC1Name(rs.getString(2));
				
				cate1s.add(cate1);
			}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("cate1 error..");
		}
		return cate1s;
	}
	
	
	public int insertNoticeArticle(ArticleVO article) {
		int parent = 0;
		try{
			logger.info("insertNoticeArticle start...");
			conn = getConnection();
			
			// 트랜젝션 시작
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(Sql.INSERT_NOTICE_ARTICLE);
			
			psmt.setString(1, article.getCate1());
			psmt.setString(2, article.getTitle());
			psmt.setString(3, article.getContent());
			psmt.setString(4, article.getUid());
			psmt.setString(5, article.getRegip());
			
			psmt.executeUpdate(); // INSERT
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO_n); // SELECT
			
			// 작업확정
			conn.commit(); 
			
			if(rs.next()){
				parent = rs.getInt(1);				
			}
			
			close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return parent;
	}
	
	
	public int selectCountTotalNotice() {
		int total =0;	
		try{
			logger.info("selectCountTotal start...");
			conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs =stmt.executeQuery(Sql.SELECT_PRODUCT_COUNT_TOTAL_NOTICE);		
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	
	public List<ArticleVO> SelectNotice(int start) {
		
		List<ArticleVO> av = new ArrayList<>();
	
		try {
			logger.info("SELECT_NOTICE start...");
			
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_NOTICE);
			psmt.setInt(1,start);	
			ResultSet rs =psmt.executeQuery();	
			
			while(rs.next()) {
				ArticleVO pv = new ArticleVO();
				
				pv.setNo(rs.getInt(1));
				pv.setParent(rs.getInt(2));
				pv.setComment(rs.getInt(3));
				pv.setCate1(rs.getString(4));
				pv.setCate2(rs.getString(5));
				pv.setTitle(rs.getString(6));
				pv.setContent(rs.getString(7));
				pv.setFile(rs.getInt(8));
				pv.setHit(rs.getInt(9));
				pv.setUid(rs.getString(10));
				pv.setRegip(rs.getString(11));
				pv.setRdate(rs.getString(12));
		
				av.add(pv);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}	
		return av;
	}
	
}
