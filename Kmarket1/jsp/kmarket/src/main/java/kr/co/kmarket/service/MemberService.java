package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;


public enum MemberService {
	INSTANCE;
	private MemberDAO dao = MemberDAO.getInstance();
	
	public void InsertMember(MemberVO mv) {
		dao.InsertMember(mv);
	}
	public MemberVO selectMember(String uid, String pass) {
		return dao.Select_Member(uid, pass);
	}
	public MemberVO Select_Member_admin(String uid) {
		return dao.Select_Member_admin(uid);
	}
	public MemberVO selectMemberBySessId(String sessId) {
		return dao.selectMemberBySessId(sessId);
	}
	public void InsertMemberSeller(MemberVO vo) {
		dao.InsertMemberSeller(vo);
	}
	public TermsVO SelectTerms() {
		return dao.SelectTerms();
	}
	public void updateMemberForSession(String uid, String sessId) {
		dao.updateMemberForSession(uid, sessId);
	}
	public void updateMemberForSessionOut(String uid) {
		dao.updateMemberForSessionOut(uid);
	}
}
