<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./_header.jsp"/>
<script src="/kmarket/js/postcode.js"></script>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		$('input[class=confirm]').click(function(){
			//alert('dd');
			let uid = $('input[name=uid]').val();
			let pass = $('input[name=pass]').val();
			
			let jsonData = { 'uid' : uid ,
							 'pass' : pass	
			};
			
			$.ajax({
				url : '/kmarket/member/mypage.do',
				method : 'POST',
				data : jsonData,
				dataType : 'json',
				success : function(data){
					if(data.result > 0){
						location.href="/kmarket/member/mypageinfo.do";
					}else{
						alert('비밀번호가 일치하지 않습니다.');						
					}
				}
			});
		});
	});

</script>
        <main id="member">
            <div class="register">
                <nav>
                    <h1>회원정보 확인</h1>
                </nav>
				<form action="#" method="POST">
					<section>
						<table class="myPage">
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td><input type="text" name="uid" placeholder="아이디를 입력"
									required /> <span class="msgId">아이디를 입력해주세요.</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td><input type="password" name="pass" placeholder="비밀번호를 입력"
									required /> <span class="msgPass">비밀번호를 입력해주세요</span></td>
							</tr>
						</table>
					</section>
					<div>
						<input type="submit" class="confirm" value="다음">
					</div>
				</form>
            </div>
        </main>
<jsp:include page="./_footer.jsp"/>     