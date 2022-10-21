<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String bookId    = request.getParameter("bookId");
	String bookName   = request.getParameter("bookName");
	String publisher  = request.getParameter("publisher");
	String price = request.getParameter("price");

	// 데이터베이스 작업
	try{
		// 1단계 - JNDI 서비스 객체생성
		Context initCtx = new InitialContext();
		Context ctx = (Context)initCtx.lookup("java:comp/env"); // JNDI 서비스를 사용하기 위한 기본 이름
		
		// 2단계 - 커넥션 풀에서 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("dbcp_java1_bookstore"); // 커넥션 풀 가져오기
		Connection conn = ds.getConnection(); // 커넥션 가져오기
		
		// 3단계
		String sql = "DELETE FROM `book` WHERE `bookId`=?";
	 	PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookId);
		
		// 4단계
		psmt.executeUpdate();
		// 5단계
		// 6단계
		psmt.close();
		conn.close(); // 반납
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>

















