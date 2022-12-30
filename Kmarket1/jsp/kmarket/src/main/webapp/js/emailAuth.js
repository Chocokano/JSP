 //이메일
let isEmailAuthOk = true;
let preventDoubleClick = false;
let receivedCode = 0;
$(function(){
	

	/* 이메일인증 */
	$('#btnEmail').click(function(){
		let email = $('input[name=email]').val();
		
		if(email == ''){
			alert('이메일을 입력해주세요.');
			return;
		}
		
		if(preventDoubleClick){ 
			return;
		}
		preventDoubleClick = true;
		
		setTimeout(function(){
			$.ajax({
				url: '/kmarket/member/emailAuth.do',
				method: 'GET',
				data: {"email": email},
				dataType: 'json',
				success: function(data){
					if(data.status > 0){
						//메일전송 성공
						$('input[name=auth]').removeAttr("disabled")
						receivedCode = data.code;
					}else{
						//메일전송 실패
						alert('메일전송이 실패 했습니다. \n다시 시도 하시기 바랍니다.');
					}
				}
			});
		}, 1000);
	});
		
	//이메일 인증 확인
	$('#btnEmailConfirm').click(function(){
		let code = $('input[name=auth]').val();
		
		if(code == ''){
			alert('이메일 확인 후 코드를 입력해주세요.');
			return;
		}
		
		if(code == receivedCode){
			isEmailAuthOk = true;
			$('input[name=email]').attr('readonly', true);
			$('input[name=auth]').hide();
			$('#btnEmailConfirm').hide();
		}
		
	});
	
	//아이디 찾기 버튼
	$('.btnNext').click(function(){
		//이메일 인증코드 검증
		if(!isEmailAuthOk){
			alert('이메일을 인증 하십시오.');
			return false;
		}
		
		let email =  $('input[name=email]').val();
		let name =  $('input[name=name]').val();
		
		let jsonData = {
			"email": email,
			 "name": name
		}
		
		console.log('here1 : ' + jsonData);
		$.ajax({
			url: '/JBoard2/user/findId.do',
			method: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					console.log('here2');
					location.href = "/JBoard2/user/findIdResult.do";
				}else{
					console.log('here3');
					alert('해당 사용자가 존재하지 않습니다.');
				}
			}
		});
		return false;
	});
	
});