<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.DBCP"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String custId    = request.getParameter("custId");
	String name   = request.getParameter("name");
	String adderss  = request.getParameter("address");
	String phone = request.getParameter("phone");

	try{
		Connection conn = DBCP.getConnection();
		
		String sql = "update `customer` set `name`=?, `address`=?, `phone`=? ";
		       sql += "where `custId`=?";
		       
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, adderss);
		psmt.setString(3, phone);
		psmt.setString(4, custId);
	
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
	
%>


