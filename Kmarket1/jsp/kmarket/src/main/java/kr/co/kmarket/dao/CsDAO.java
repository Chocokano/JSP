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


public class CsDAO  extends DBHelper {
	private static CsDAO instance = new CsDAO();
	
	public static CsDAO getInstance() {
		return instance;
	}
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertQnaArticle(ArticleVO article) {
		int parent = 0;
		try{
			logger.info("insertQnaArticle start...");
			conn = getConnection();
			
			// 트랜젝션 시작
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(Sql.INSERT_QNA_ARTICLE);
			
			psmt.setString(1, article.getCate1());
			psmt.setString(2, article.getCate2());
			psmt.setString(3, article.getTitle());
			psmt.setString(4, article.getContent());
			psmt.setString(5, article.getUid());
			psmt.setString(6, article.getRegip());
			
			psmt.executeUpdate(); // INSERT
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO); // SELECT
			
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
	
	public List<ArticleVO> selectQnaArticles(String cate1, String cate2, int start) {
		
		List<ArticleVO> articles = new ArrayList<>();	
		
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_QNA_ARTICLES);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setInt(3, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				ArticleVO article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate1(rs.getString(4));
				article.setCate2(rs.getString(3));
				article.setTitle(rs.getString(4));
				article.setContent(rs.getString(5));
				article.setFile(rs.getInt(6));
				article.setHit(rs.getInt(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				
				articles.add(article);
			}
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return articles;
	}
	
	
	public List<ArticleVO> SelectArticleQna(String cate) {
		List<ArticleVO> ArticleQna = new ArrayList<>();
		try {
			logger.info("SelectArticleQna ...");
			conn = getConnection();
			
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_QNA);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setFile(rs.getInt(8));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRegip(rs.getString(11));
				av.setRdate(rs.getString(12));
				
				ArticleQna.add(av);
			}
			close();
		}catch(Exception e) {
			logger.error("SelectArticleQna error ...");			
			logger.error(e.getMessage());			
		}
		return ArticleQna;
	}
	
	public List<ArticleVO> SelectArticleQna() {
		List<ArticleVO> ArticleQna = new ArrayList<>();
		try {
			logger.info("SelectArticleQna ...");
			conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_ARTICLE_QNA_INDEX);
			
			while(rs.next()) {
				ArticleVO av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setFile(rs.getInt(8));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRegip(rs.getString(11));
				av.setRdate(rs.getString(12));
				
				ArticleQna.add(av);
			}
			close();
		}catch(Exception e) {
			logger.error("SelectArticleQna error ...");			
			logger.error(e.getMessage());			
		}
		return ArticleQna;
	}
	
	public List<ArticleVO> SelectQnaListPage(String cate, int start) {
		List<ArticleVO> ArticleQna = new ArrayList<>();
		try {
			logger.info("SelectArticleQnaPage ...");
			conn = getConnection();
			if(cate.equals("0")) {
				psmt = conn.prepareStatement(Sql.SELECT_CS_QNA_VIEW_PAGE_I);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_CS_QNA_VIEW_PAGE);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
			}
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setFile(rs.getInt(8));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRegip(rs.getString(11));
				av.setRdate(rs.getString(12));
				
				ArticleQna.add(av);
			}
			close();
		}catch(Exception e) {
			logger.error("SelectArticleQnaPage error ...");			
			logger.error(e.getMessage());			
		}
		return ArticleQna;
	}
	
	public int selectCountTotal(String cate1) {
		int total = 0;
		try {
			conn = getConnection();
			if(cate1.equals("0")) {
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL2);
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate1);
				rs = psmt.executeQuery();
			}
			
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	
	//admin category1
	public List<Cate1VO> SelectCsCate1() {
		
		List<Cate1VO> cate1s = new ArrayList<>();
		
		try {
			logger.info("select Cs cate1 start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE1_CS);
		
			
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
public List<Cate2VO> SelectCsCate2() {
		
		List<Cate2VO> cate2s = new ArrayList<>();
		
		try {
			logger.info("select Cs cate2 start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE2_CS_INDEX);
					
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
			logger.error("cate2 error..");
		}
		return cate2s;
	}
	
	public List<Cate2VO> SelectCsCate2(String cate1) {
		
		List<Cate2VO> cate2s = new ArrayList<>();
		
		try {
			logger.info("select cate2 start...");
			
			conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_CATE2_CS);
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
	
	public ArticleVO QnaView(String no) {
		
		ArticleVO av = null;
		
		try {
			logger.info("select CsView start...");
			
			conn = getConnection();		
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_QNA_VIEW);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
		
			if(rs.next()) {
				av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRdate(rs.getString(12));
				}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("csView error..");
		}
		return av;
	}
	
	
	// notice
	public List<ArticleVO> SelectNoticeList(String cate, int start) {
		List<ArticleVO> ArticleNotice = new ArrayList<>();
		try {
			logger.info("SelectArticleNotice ...");
			conn = getConnection();
			ResultSet rs = null;
			PreparedStatement psmt =null;
			if(cate.equals("10") ) {
				psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_NOTICE_TOTAL);
				psmt.setInt(1, start);			
			}
			else {
				psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_NOTICE_CATE);
				psmt.setString(1, cate);
				psmt.setInt(2, start);		
			}
			rs = psmt.executeQuery();		
			while(rs.next()) {
				ArticleVO av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setParent(rs.getInt(2));
				av.setComment(rs.getInt(3));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setFile(rs.getInt(8));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRegip(rs.getString(11));
				av.setRdate(rs.getString(12));
				
				ArticleNotice.add(av);
			}
			close();
		}catch(Exception e) {
			logger.error("SelectArticleNotice error ...");			
			logger.error(e.getMessage());			
		}
		return ArticleNotice;
	}
	
	// notice cate
	public List<Cate1VO> SelectCsCateNotice() {
		
		List<Cate1VO> cate1s = new ArrayList<>();
		
		try {
			logger.info("select Cs Notice cate start...");
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Sql.SELECT_CATE_CS_NOTICE);
					
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
	
	// notice count
	public int selectCountNotice(String cate1) {
		int total = 0;
		try {
			logger.info("select Cs Notice count start...");
			conn = getConnection();
			
			if(cate1.equals("10")) {
				rs = stmt.executeQuery(Sql.SELECT_COUNT_NOTICE_TOTAL);			
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_NOTICE);
				psmt.setString(1, cate1);
				rs = psmt.executeQuery();	
			}
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			close();	
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	// notice view
public ArticleVO NoticeView(String no) {
		
		ArticleVO av = null;
		
		try {
			logger.info("select NoticeView start...");
			
			conn = getConnection();		
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_NOTICE_VIEW);
			psmt.setString(1, no);
			ResultSet rs = psmt.executeQuery();
		
			if(rs.next()) {
				av = new ArticleVO();
				av.setNo(rs.getInt(1));
				av.setCate1(rs.getString(4));
				av.setCate2(rs.getString(5));
				av.setTitle(rs.getString(6));
				av.setContent(rs.getString(7));
				av.setHit(rs.getInt(9));
				av.setUid(rs.getString(10));
				av.setRdate(rs.getString(12));
				}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			logger.error("NoticeView error..");
		}
		return av;
	}
}
