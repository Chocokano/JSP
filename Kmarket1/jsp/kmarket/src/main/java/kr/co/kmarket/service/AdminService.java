package kr.co.kmarket.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.vo.ArticleVO;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;
import kr.co.kmarket.vo.ProductVO;

public enum AdminService {
	INSTANCE;
	private AdminDAO dao = AdminDAO.getInstance();
	
	// cate data 불러오기
	public List<Cate1VO> SelectAdminProduct1() {
		return dao.SelectAdminProduct1();
	}
	public List<ProductVO> SelectProductList(String cate1, String cate2, String cate, int start) {
		return dao.SelectProductList(cate1, cate2, cate, start);
	}
	
	public List<Cate2VO> SelectAdminProduct2(String cate1) {
		return dao.SelectAdminProduct2(cate1);
	}
	public List<Cate2VO> SelectAdminProduct2() {
		return dao.SelectAdminProduct2();
	}
	public void INSERT_ADMIN_PRODUCT(ProductVO pv) {
		dao.INSERT_ADMIN_PRODUCT(pv);
	}
	public List<Cate1VO> SelectCate1Name(String cate1) {
		return 	dao.SelectCate1Name(cate1);
	}
	public List<Cate2VO> SelectCate2Name(String cate1, String cate2) {
		return 	dao.SelectCate2Name(cate1, cate2);
	}
	
	// 경로 설정
	public MultipartRequest uploadFile(HttpServletRequest req, String path) throws IOException {
		int maxSize = 1024 * 1024 * 10; // 최대 파일 업로드 허용량 10MB
		return new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	}
	public MultipartRequest uploadFile2(HttpServletRequest req, String path) throws IOException {
		int maxSize = 1024 * 1024 * 10; // 최대 파일 업로드 허용량 10MB
		return new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	}
	// 파일명 수정
	public String renameFile(String thumbName, String path, String cate1, String cate2) {

		// 파일명 수정
		int idx = thumbName.lastIndexOf(".");
		String ext = thumbName.substring(idx);
		
		String now = UUID.randomUUID().toString();
		String newName = now+ext; // random.txt 
		
		File oriFile = new File(path+"/"+thumbName);
		File newFile = new File(path+"/"+cate1+"/"+cate2+"/"+newName);	
			
		oriFile.renameTo(newFile);
		
		return newName;
	}
	
	public List<ProductVO> SelectProduct(int code) {
		return dao.SelectProduct(code);
	}
	
	public List<ProductVO> SelectProductAdmin(String seller, int start) {
		return dao.SelectProductAdmin(seller, start);
	}
	
	// 페이지 list 넘버
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total / 10 + 1;
		}
		
		return lastPageNum;
	}
	
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null){
			currentPage = Integer.parseInt(pg);	
		}
		
		return currentPage;
	}
	
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	public int selectCountTotal(String cate1, String cate2) {
		return dao.selectCountTotal(cate1, cate2);
	}
	public int selectCountTotal(String seller) {
		return dao.selectCountTotal(seller);
	}
	public int selectCountTotalNotice() {
		return dao.selectCountTotalNotice();
	}
	public List<Cate1VO> SelectNoticeCate1() {
		return dao.SelectNoticeCate1();
	}
	public int insertNoticeArticle(ArticleVO article) {
		return dao.insertNoticeArticle(article);
	}
	public List<ArticleVO> SelectNotice(int start) {
		return dao.SelectNotice(start);
	}

}
