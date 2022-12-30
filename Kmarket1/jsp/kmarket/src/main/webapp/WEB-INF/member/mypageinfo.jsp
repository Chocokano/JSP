<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./_header.jsp"/>
<script src="/kmarket/js/postcode.js"></script>
<script src="/kmarket/js/mypage.js"></script>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <main id="member">
            <div class="register">
                <nav>
                    <h1>마이페이지</h1>
                </nav>
				<form action="/kmarket/member/mypageinfo.do" method="POST">
					<section>
						<table>
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td><input type="text" name="uid" value="${sessUser.uid }" placeholder="아이디를 입력"
									required readonly="readonly" />
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td><input type="password" name="pass1" placeholder="비밀번호를 입력"/> 
								<span class="msgPass"></span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호확인</th>
								<td><input type="password" name="pass2" placeholder="비밀번호를 확인"/> 
									<button type="button" class="btn btnChangePass" style="padding: 5px; margin-left: 5px;">비밀번호 수정</button>
								</td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>기본 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>이름</th>
								<td><input type="text" name="name" value="${sessUser.name }" placeholder="이름을 입력"
									required /></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>성별</th>
								<td><label><input type="radio" name="gender"
										value="1" checked>&nbsp;남</label> <label><input
										type="radio" name="gender" value="2">&nbsp;여</label></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>EMAIL</th>
								<td><input type="email" name="email" value="${sessUser.email }" placeholder="이메일 입력"
									required />
									<button type="button" id="btnEmail">인증번호 받기</button>
									<span class="resultEmail"></span>
									<div class="auth" style="display: none">
			                            <input type="text" name="auth" placeholder="인증번호 입력"/>
			                            <button type="button" id="btnEmailConfirm">확인</button>
			                        </div>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>휴대폰</th>
								<td><input type="text" name="hp" maxlength="13" value="${sessUser.hp }"
									placeholder="휴대폰번호 입력" required /> <span class="msgHp"> -
										포함 13자리를 입력하세요.</span></td>
							</tr>
							<tr class="addr">
								<th>주소</th>
								<td>
									<div>
										<input type="text" name="zip" id="zip" placeholder="우편번호 입력"
											readonly />
										<button type="button" onclick="postcode()" style="padding: 4px;" >우편번호찾기</button>

									</div>
									<div>
										<input type="text" name="addr1" id="addr1" size="50"
											placeholder="주소를 검색하세요." readonly />
									</div>
									<div>
										<input type="text" name="addr2" id="addr2" size="50"
											placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
							<tr>
								<td>회원탈퇴</td>
								<td>
									<button type="button" class="btn btnDeleteAccount"
									style="padding: 6px;">
									회원탈퇴</button>
								</td>
							</tr>
						</table>
					</section>
					<div>
						<input type="submit" class="Edit" value="회원정보 수정" />
					</div>
				</form>
            </div>
        </main>   
<jsp:include page="./_footer.jsp"/>     