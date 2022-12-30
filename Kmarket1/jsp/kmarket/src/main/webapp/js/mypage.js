let regPass = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
let regNick = /^[가-힣A-Za-z0-9]{2,10}$/;
let regHp 	= /^\d{3}-\d{3,4}-\d{4}$/;

	$(document).ready(function(){
		$('.btnChangePass').click(function(){
			//alert('dd');
			
			let uid = $('input[name=uid]').val();
			let pass1 = $('input[name=pass1]').val();
			let pass2 = $('input[name=pass2]').val();
			
			if(pass1 != pass2){ //비밀번호 일치여부
				alert('비밀번호가 일치하지 않습니다.');
				return false;
				
			}else if(!pass2.match(regPass)){ //비밀번호 유효성 검사
				alert('유효성 검사에 맞지 않습니다.\n특수문자,대·소문자 8~20글자 조합해주세요.');
			
			}else{
				
				let jsonData = {
						'pass' : pass2 ,
						'uid'  : uid
				};
				
				$.ajax({
					url : '/kmarket/member/findPwChange.do',
					method : 'POST',
					data : jsonData,
					dataType : 'json',
					success : function(data){
						if(data.result > 0){
							$('.msgPass').css('color','green').text('비밀번호가 변경되었습니다.');
							$('input[name=pass1]').val('');
							$('input[name=pass2]').val('');
						}
					}
				});
			}
		});
		
			$('#btnEmail').click(function(){
			
			$('div[class=auth]').show();
			let email = $('input[name=email]').val();
			
			if(email == ''){
				alert('이메일을 입력해주세요.');
				retrun;
			}else{
				$('.resultEmail').css('color','green').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
					
				let jsonData = {
						'email' : email
				};
				
				$.ajax({
					url: '/kmarket/member/emailAuth.do',
					method: 'GET',
					data: jsonData ,
					dataType: 'json',
					success: function(data){
						if(data.status > 0){
							//메일전송 성공
							$('input[name=auth]').removeAttr("disabled")
							receivedCode = data.code;
						}else{
							//메일전송실패
							alert('메일전송이 실패했습니다.\n다시 시도하시길 바랍니다.');
						}
					}
				});			
			}
		});
		
		$('#btnEmailConfirm').click(function(){
			let code = $('input[name=auth]').val();
				//alert('d');
				if(code == ''){
					alert('인증번호를 입력해주세요.');
				}
				
				if(code == receivedCode){
					isEmailAuthOk = true;
					$('input[name=email]').attr('readonly', true);
					$('.resultEmail').text('이메일이 인증되었습니다.');
					$('input[name=auth]').hide();
					$('#btnEmailConfirm').hide();
				}
		});
	});
