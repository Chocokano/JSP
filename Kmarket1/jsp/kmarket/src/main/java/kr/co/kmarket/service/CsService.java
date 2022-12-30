package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;


public enum CsService {
	INSTANCE;
	private CsDAO dao = CsDAO.getInstance();
	
	public List<ArticleVO> SelectArticleQna(String cate) {
		return dao.SelectArticleQna(cate);
	}
	public int insertQnaArticle(ArticleVO article) {
		return dao.insertQnaArticle(article);
	}
	public List<ArticleVO> selectQnaArticles(String cate1, String cate2, int start){
		return dao.selectQnaArticles(cate1, cate2, start);
	}
	public int getCurrentPage(String pg) {
		int currentPage = 1;	// 현재 페이지 기본값
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		if(total % 10 == 0) {
			lastPageNum = (total/10);
		}else {
			lastPageNum = (total/10)+1;
		}
		return lastPageNum;
	}
	public int getLimitStart(int currentPage) {
		int limitStart = 0;
		limitStart = (currentPage-1)*10;
		return limitStart;
	}
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int pageGroupCurrent = (int) Math.ceil(currentPage/10.0);
		int pageGroupStart = (pageGroupCurrent - 1)*10+1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart,pageGroupEnd};
		return result;
	}
	public int getPageStartNum(int total, int limitStart) {
		int pageStartNum = 0;
		pageStartNum = total - limitStart;
		return pageStartNum;
	}
	public int getStartNum(int currentPage) {
		return (currentPage-1)*10;
	}
	
	public List<Cate1VO> SelectCsCate1() {
		return dao.SelectCsCate1();
	}
	public List<Cate2VO> SelectCsCate2(String cate1) {
		return dao.SelectCsCate2(cate1);
	}
	public ArticleVO QnaView(String no) {
		return dao.QnaView(no);
	}
	public List<ArticleVO> SelectQnaListPage(String cate, int start) {
		return dao.SelectQnaListPage(cate, start);
	}
	public List<Cate2VO> SelectCsCate2() {
		return dao.SelectCsCate2();
	}
	public List<ArticleVO> SelectArticleQna() {
		return dao.SelectArticleQna();
	}
	public List<ArticleVO> SelectNoticeList(String cate, int start) {
		return dao.SelectNoticeList(cate, start);
	}
	public List<Cate1VO> SelectCsCateNotice() {
		return dao.SelectCsCateNotice();
	}
	public int selectCountNotice(String cate1) {
		return dao.selectCountNotice(cate1);
	}
	public ArticleVO NoticeView(String no) {
		return dao.NoticeView(no);
	}
}
