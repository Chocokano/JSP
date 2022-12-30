/**
 * 
 */
let regUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let regName  = /^[가-힣]{2,4}$/;
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
let regHp	 = /^\d{3}-\d{3,4}-\d{4}$/;
let regPass  = /^.*(?=^.{5,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isEmailOk = false;
let isHpOk    = false;
let isEmailAuthOk = false;
let isEmailAuthCodeOk = false;
let receivedCode = 0;	

$(function(){
	
	$('input[name=uid]').keydown(function(){
	isUidOk = false;
	
	});
	
	$('#btnUidCheck').click(function(){
		
		let uid = $('input[name=uid]').val();
		
		if(isUidOk){
			return;
		}
		
		if(!uid.match(regUid)){
			isUidOk = false;
			$('.resultUid').css('color', 'red').text('아이디가 유효하지 않습니다.');
			return;
		}
		
		let jsonData = {"uid":uid};
		
		$('.resultUid').css('color', 'black').text('...');
		
		setTimeout(()=>{
			
			$.ajax({
				url: '/kmarket/member/checkUid.do',
				method: 'get',
				data: jsonData,
				dataType: 'json',
				success:function(data){

					if(data.result == 0){
						isUidOk = true;
						$('.resultUid').css('color', 'green').text('사용 가능한 아이디 입니다.');
					}else{
						isUidOk = false;
						$('.resultUid').css('color', 'red').text('이미 사용중인 아이디 입니다.');
					}
				}
			});
			
		}, 500);
	});	
	
	$('input[name=pass2]').focusout(function(){			
		let pass1 = $('input[name=pass1]').val();
		let pass2 = $(this).val();
		
		if(pass1==pass2){
							
			if(pass2.match(regPass)){
				isPassOk = true;
				$('.resultPass').css('color', 'green').text('비밀번호가 일치합니다.');	
				return;
			}else{
				isPassOk = false;
				$('.resultPass').css('color', 'red').text('영문, 숫자, 특수문자 조합 최소 5자 이상 이어야 합니다.');
			}				
			
		}else{
			isPassOk = false;
			$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			return;
		}			
	});
	
	$('input[name=name]').focusout(function(){
		
		let name = $(this).val();
		
		if(!name.match(regName)){
			isNameOk = false;
			$('.resultName').css('color', 'red').text('이름은 한글 2자 이상 이어야 합니다.');
		}else{
			isNameOk = true;
			$('.resultName').text('');
		}
	});
	
	$('input[name=email]').focusout(function(){
		let email = $(this).val();
		
		if(!email.match(regEmail)){
			isEmailOk = false;
			$('.resultEmail').css('color', 'red').text('이메일이 유효하지 않습니다.');
		}else{
			isEmailOk = true;
			$('.resultEmail').text('');
		}			
	});
	$('#btnEmail').click(function(){
		
		$(this).hide();			
		let email = $('input[name=email]').val();
		console.log('here1 : ' + email);
		
		if(email == ''){
			alert('이메일을 입력 하세요.');
			return;
		}
		
		if(isEmailAuthOk){
			console.log('here2');
			return;
		}
		
		isEmailAuthOk = true;
		
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		console.log('here3');
		
		setTimeout(function(){
			console.log('here4');
			
			$.ajax({
				url: '/kmarket/member/emailAuth.do',
				method: 'GET',
				data: {"email": email},
				dataType: 'json',
				success: function(data){
					
					if(data.status > 0){
						console.log('here5');
						isEmailAuthOk = true;
						$('.resultEmail').text('이메일을 확인 후 인증코드를 입력하세요.');
						$('.auth').show();
						receivedCode = data.code;
						
					}else{
						console.log('here6');
						isEmailAuthOk = false;
						alert('메일전송이 실패 했습니다.\n다시 시도 하시기 바랍니다.');
					}
				}
			});
		}, 1000);
	});
	
	
	// 이메일 인증코드 확인 버튼
	$('#btnEmailConfirm').click(function(){
		
		let code = $('input[name=auth]').val();
		
		if(code == ''){
			alert('이메일 확인 후 인증코드를 입력하세요.');
			return;
		}
		
		if(code == receivedCode){
			isEmailAuthCodeOk = true;
			$('input[name=email]').attr('readonly', true);
			$('.resultEmail').text('이메일이 인증 되었습니다.');				
			$('.auth').hide();
		}else{
			isEmailAuthCodeOk = false;
			alert('인증코드가 틀립니다.\n다시 확인 하십시요.');
		}
	});
	
	$('input[name=hp]').focusout(function(){
		let hp = $(this).val();
		
		if(!hp.match(regHp)){
			isHpOk = false;
			$('.resultHp').css('color', 'red').text('휴대폰이 유효하지 않습니다.');
		}else{
			isHpOk = true;
			$('.resultHp').text('');
		}
	});
	
	$('.register > form').submit(function(){
					
		if(!isUidOk){
			alert('아이디를 확인 하십시요.');
			return false;
		}
		if(!isPassOk){
			alert('비밀번호를 확인 하십시요.');
			return false;
		}
		if(!isNameOk){
			alert('이름을 확인 하십시요.');
			return false;
		}
		if(!isEmailOk){
			alert('이메일을 확인 하십시요.');
			return false;
		}
		if(!isEmailAuthCodeOk){
			alert('이메일을 인증을 수행 하십시요.');
			return false;
		}
		if(!isHpOk){
			alert('휴대폰을 확인 하십시요.');
			return false;
		}
		
		return true;
		});
});