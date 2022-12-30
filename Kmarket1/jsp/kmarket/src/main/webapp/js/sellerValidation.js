/**
 * 
 */
let regUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let regName  = /^[가-힣]{2,4}$/;
let regCeo  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;;
let regCompany  = /\B(주)\S+/g;
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
let regHp	 = /^\d{3}-\d{3,4}-\d{4}$/;
let regTel	 = /^\d{2,3}-\d{3}-\d{4}$/;
let regFax	 = /^\d{2,3}-\d{3}-\d{4}$/;
let regCorp	 = /^\d{3}-\d{2}-\d{6}$/;
let regPass  = /^.*(?=^.{5,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
let reOnline = /^([ㄱ-힣]{2,2})-(\d{5,5})$/;

let isUidOk   = false;
let isNameOk   = false;
let isCeoOk   = false;
let isPassOk  = false;
let isCompanyOk  = false;
let isCorpOk  = false;
let isOnlieOk  = false;
let isTelOk  = false;
let isHpOk  = false;
let isFaxOk  = false;
let isEmailOk  = false;
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
	
	$('input[name=company]').focusout(function(){
		
		let company = $(this).val();
		
		if(!company.match(regCompany)){
			isCompanyOk = false;
			$('.resultCompany').css('color', 'red').text(' (주)포함 입력해주십시오, 예) (주)케이마켓.');
		}else{
			isCompanyOk = true;
			$('.resultCompany').text('ok');
		}
	});
	
	$('input[name=ceo]').focusout(function(){
		
		let ceo = $(this).val();
		
		if(!ceo.match(regCeo)){
			isCeoOk = false;
			$('.resultCeo').css('color', 'red').text('대표명이 유효하지 않습니다.');
		}else{
			isCeoOk = true;
			$('.resultCeo').text('ok');
		}
	});
	
	
		$('input[name=corp_reg]').focusout(function(){
		let corp = $(this).val();
		
		if(!corp.match(regCorp)){
			isCorpOk = false;
			$('.resultCorp').css('color', 'red').text('- 표시 포함 12자리 입력해주십시오, 예) 123-12-123456');
		}else{
			isCorpOk = true;
			$('.resultCorp').text('ok');
		}
	});
	
	$('input[name=online_reg]').focusout(function(){
		
		let online_reg = $(this).val();
		
		if(!online_reg.match(reOnline)){
			isOnlineOk = false;
			$('.resultOnline').css('color', 'red').text('- 표시 포함 입력해주십시오, 예) 강남-12345');
		}else{
			isOnlineOk = true;
			$('.resultOnline').text('ok');
		}
	});
	
	$('input[name=tel]').focusout(function(){
		let tel = $(this).val();
		
		if(!tel.match(regTel)){
			isTelOk = false;
			$('.resultTel').css('color', 'red').text('- 표시 포함 입력해주십시오, 지역번호 포함, 예) 12-123-1234');
		}else{
			isTelOk = true;
			$('.resultTel').text('ok');
		}
	});
	
	$('input[name=fax]').focusout(function(){
		let fax = $(this).val();
		
		if(!fax.match(regTel)){
			isFaxOk = false;
			$('.resultFax').css('color', 'red').text('- 표시 포함 입력해주십시오, 지역번호 포함, 예) 12-123-1234');
		}else{
			isFaxOk = true;
			$('.resultFax').text('ok');
		}
	});
	
	$('input[name=email]').focusout(function(){
		let email = $(this).val();
		
		if(!email.match(regEmail)){
			isEmailOk = false;
			$('.resultEmail').css('color', 'red').text('이메일이 유효하지 않습니다.');
		}else{
			isEmailOk = true;
			$('.resultEmail').text('ok');
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
	
	$('input[name=hp]').focusout(function(){
		let hp = $(this).val();
		
		if(!hp.match(regHp)){
			isHpOk = false;
			$('.resultHp').css('color', 'red').text('- 포함 13자리를 입력하세요.');
		}else{
			isHpOk = true;
			$('.resultHp').text('');
		}
	});
	
	$('.registerSeller > form').submit(function(){
					
		if(!isUidOk){
			alert('아이디를 확인 하십시요.');
			return false;
		}
		if(!isPassOk){
			alert('비밀번호를 확인 하십시요.');
			return false;
		}
		if(!isCompanyOk){
			alert('회사명을 확인 하십시요.');
			return false;
		}
		if(!isCeoOk){
			alert('대표자를 확인 하십시요.');
			return false;
		}
		if(!isCorpOk){
			alert('사업자등록번호를 확인 하십시요.');
			return false;
		}
		if(!isOnlineOk){
			alert('통신판매업신고을 확인 하십시요.');
			return false;
		}
		if(!isTelOk){
			alert('전화번호를 확인 하십시요.');
			return false;
		}
		if(!isFaxOk){
			alert('팩스번호를 확인 하십시요.');
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
		if(!isNameOk){
			alert('담당자 이름을 확인 하십시요.');
			return false;
		}
		if(!isHpOk){
			alert('담당자 휴대폰을 확인 하십시요.');
			return false;
		}
		
		return true;
		});
	
	
});