<%@page import="kr.co.college.bean.RegisterBean"%>
<%@page import="kr.co.college.db.DBCP"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<RegisterBean> registers = new ArrayList<>(); 

	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from `register`");
		
		while(rs.next()){
			RegisterBean rb = new RegisterBean();
			rb.setRegStdNo(rs.getInt(1));
			rb.setRegLecNo(rs.getInt(2));
			rb.setRegMidScore(rs.getInt(3));
			rb.setRegFinalScore(rs.getInt(4));
			rb.setRegTotalScore(rs.getInt(5));
			rb.setRegGrade(rs.getString(6));
			
			registers.add(rb);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
				
				$('.btnOpen').click(function(){
					$('section').show();
				});
				
				$('.btnClose').click(function(){
					$('section').hide();	
				});
				
				// 최종 등록 버튼
				$('input[type=submit]').click(function(){
					
					let lecNo      = $('input[name=lecNo]').val();
					let lecName    = $('input[name=lecName]').val();
					let lecCredit = $('input[name=lecCredit]').val();
					let lecTime    = $('input[name=lecTime]').val();
					let lecClass   = $('input[name=lecClass]').val();
					
					let jsonData = {
						"lecNo": lecNo,
						"lecName": lecName,
						"lecCredit": lecCredit,
						"lecTime": lecTime,
						"lecClass": lecClass
					};
					
					console.log('jsonData : ' + jsonData);
					
					$.ajax({
						url: './lectureProc.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success:function(data){
							if(data.result == 1){
								alert('완료!');
							}else{
								alert('실패!');
							}
						}
					});
				});
			});
		</script>			
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="./lecture.jsp">강좌관리</a>
		<a href="./register.jsp">수강관리</a>
		<a href="./student.jsp">학생관리</a>
		
		<h4>수강현황</h4>
		
		<tr>
			<td colspan="2" align="right"><input type="submit" class="btnOpen" value="등록"/></td>
		</tr>
		
		<table border="1">
		
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>강좌명</th>
				<th>강좌코드</th>
				<th>중간시험</th>
				<th>기말시험</th>
				<th>총점</th>
				<th>등급</th>
			</tr>
			<% for(RegisterBean rb : registers){ %>
			<tr>
				<td><%= rb.getRegStdNo() %></td>
				<td><%= rb.getRegLecNo() %></td>
				<td><%= rb.getRegMidScore() %></td>
				<td><%= rb.getRegFinalScore() %></td>
				<td><%= rb.getRegTotalScore() %></td>
				<td><%= rb.getRegGrade() %></td>
			</tr>
			<% } %>
		</table>
		
		<section style="display: none">
			<h4>수강신청</h4>			
			<table border="1">
				<button class="btnClose">닫기</button>
				<tr>
					<td>학번</td>
					<td><input type="text" name="stdNo"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="stdName"/></td>
				</tr>
				<tr>
					<td>신청강좌</td>
					<td><input type="text" name="lecName"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="신청"/></td>
				</tr>
			</table>
		</section>
		
	</body>
</html>









