<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="./header.jsp"></jsp:include>
<script>

	$(function(){
		$('input[class=cart]').click(function(){
			
			let uid = $('input[name=uid]').val();
			let proNo = $('input[name=proNo]').val();
			let count = $('input[name=num]').val();
			let price = $('input[name=ori_price]').val();
			let discount = $('input[name=discount]').val();
			let delivery =  $('input[name=delivery]').val();
			discount = Number(discount);
			
			let jsonData = {
					'uid' : uid,
					'proNo' : proNo,
					'count' : count,
					'price' : price,
					'discount' : discount,
					'delivery' : delivery
			};
			
			//로그인 유무 확인
			if(uid == ''){
				alert('로그인 후 이용가능합니다.');
				location.href= '/kmarket/member/login.do';
				
			}else{
				
				$.ajax({
					url : '/kmarket/product/view.do' ,
					method : 'POST' ,
					data : jsonData ,
					dataType : 'json',
					success : function(data){
						if(data.result > 0){
							alert('장바구니에 추가되었습니다. \n장바구니로 이동합니다.');
							location.href = '/kmarket/product/cart.do?uid='+uid;
						}
					}
				});
			}
		});
		
		$('button[class=decrease]').click(function(){
			let count = $('input[name=num]').val();
			if(count>0){
				count--;
				document.getElementById("test").value = count;
			}else {alert("음수입니다.")}
			
		});
		$('button[class=increase]').click(function(){
			let count = $('input[name=num]').val();
			if(count>=0){
				count++;
			}
			document.getElementById("test").value = count;
		});
		
		//주문하기 페이지
		$('input[class=order]').click(function(){
			console.log("here1");
			let proName = $('input[name=proName]').val();
			let descript = $('input[name=descript]').val();
			let uid = $('input[name=uid]').val();
			let proNo = $('input[name=proNo]').val();
			let count = $('input[name=num]').val();
			let price = $('input[name=ori_price]').val();
			let discount = $('input[name=discount]').val();
			let delivery =  $('input[name=delivery]').val();
			let cate1 = $('input[name=cate1]').val();
			let cate2 = $('input[name=cate2]').val();
			let thumb1 = $('input[name=thumb1]').val();
			let total = $('input[name=total]').val();
			discount = Number(discount);
			total = Number(total);
			
			let jsonData = {
					'cate1' : cate1,
					'cate2' : cate2,
					'thumb1' : thumb1,
					'proName' : proName,
					'descript' : descript,
					'uid' : uid,
					'proNo' : proNo,
					'count' : count,
					'price' : price,
					'discount' : discount,
					'delivery' : delivery,
					'total' : total 
			};
			console.log("here3");
			//로그인 유무 확인
			if(uid == ''){
				alert('로그인 후 이용가능합니다.');
				location.href= '/kmarket/member/login.do';
				
			}else{
				console.log("here4");
				$.ajax({
					url : '/kmarket/product/directOrder.do' ,
					method : 'POST' ,
					data : jsonData ,
					dataType : 'json',
					success : function(data){
							alert('주문 페이지로 이동합니다.');
							location.href = '/kmarket/product/order.do';
					}
				});
			}
		});
	});


</script>
        <main id="product">
            <aside>
                <ul class="category">
                    <li><i class="fa fa-bars" aria-hidden="true"></i>카테고리</li>
			  <c:forEach var="cate1List" items="${cate1List}">

            	 <li>
              <a href="#">
              	<i class="fas ">
              	</i>${cate1List.c1Name}<iclass="fas fa-angle-right"></i>
              </a>
              <ol>
              	 <c:forEach var="cate2List" items="${cate2List}">
              	 	<c:if test="${cate1List.cate1 eq cate2List.cate1 }">
              	 		<li><a href="/kmarket/product/list.do?&cate1=${cate2List.cate1}&cate2=${cate2List.cate2}">${cate2List.c2Name }</a></li>            	 		
              	 	</c:if>          
                </c:forEach>
              </ol>
            </li>
            </c:forEach>
                </ul>
            </aside>

            <section class="view">
                <nav>
                    <h1>상품목록</h1>
                  	<p>HOME > <span>${productView.c1Name }</span> > <strong>${productView.c2Name }</strong></p>
                </nav>

                <!-- 상품 전체 정보 내용 -->                
                <article class="info">
                    <div class="image">
                        <img src="../file/${productView.cate1 }/${productView.cate2 }/${productView.thumb3 }" width=460px height=460px alt="상품이미지"/>
                    </div>
                    <div class="summary">
                        <nav>
                            <h1>${productView.seller }</h1>
                            <h2>상품번호&nbsp;:&nbsp;<span>10010118412</span></h2>
                        </nav>                        
                        <nav>
                            <h3>${productView.proName }</h3>
                            <input type="hidden" name="proName" value="${productView.proName }">
                            <p>${productView.descript }</p>
                            <input type="hidden" name="descript" value="${productView.descript }">
                            <h5 class="rating star${productView.score}"><a href="#">상품평보기</a></h5>
                           
                        </nav>
                        <nav>
                            <div class="org_price">
                                <del>${productView.price }</del>
                                <span>${productView.discount }%</span>
                            </div>
                            <div class="dis_price">
                                <ins>${(productView.price/100)*(100-productView.discount)  }</ins>
                            </div>
                        </nav>
                        <nav>
                        <c:choose>
                        	<c:when test="${productView.delivery eq 0}">
                        		<span class="delivery">무료배송</span>           		
                        	</c:when>
                        	<c:otherwise>
                        		<input type="text" name="delivery" value="${productView.delivery }" style="border: none;">
                        	</c:otherwise>
                        </c:choose>
                        	<span class="arrival"> ${monthValue }/${dayOfMonth } (${arriveWeek  })도착예정</span>
                        	<span class="desc">본 상품은 국내배송만 가능합니다.</span>
                        </nav>
                        <nav>
                            <span class="card cardfree"><i>아이콘</i>무이자할부</span>&nbsp;&nbsp;
                            <span class="card cardadd"><i>아이콘</i>카드추가혜택</span>
                        </nav>
                        <nav>
                            <span class="origin">원산지-상세설명 참조</span>
                        </nav>
                        <img src="../img/common/vip_plcc_banner.png" alt="100원만 결제해도 1만원 적립!" class="banner" />
                        
                        <div class="count">
                         <c:set var="i" value="1"></c:set>
                            <button class="decrease">-</button>
                            <input id="test" type="text" name="num" class="count" value="${i }" />
                            <button class="increase">+</button>

                        </div>
                        
                        <div class="total">
                            <span>${(productView.price/100)*(100-productView.discount)}</span>
                            <em>총 상품금액</em>
                        </div>

                        <div class="button">
                        	<input type="hidden" name="uid" value="${sessUser.uid }">
                        	<input type="hidden" name="proNo" value="${productView.proNo }">
                        	<input type="hidden" name="ori_price" value="${productView.price }">
                        	<input type="hidden" name="discount" value="${productView.discount}">
                        	<input type="hidden" name="delivery" value="${productView.delivery }">
                        	<input type="hidden" name="cate1" value="${productView.cate1 }">
                        	<input type="hidden" name="cate2" value="${productView.cate2 }">
                        	<input type="hidden" name="point" value="${productView.point }">
                        	<input type="hidden" name="delivery" value="${productView.delivery }">
                        	<input type="hidden" name="thumb1" value="${productView.thumb1 }">
                        	<input type="hidden" name="total" value="${(productView.price/100)*(100-productView.discount)+(productView.delivery)}">
                            <input type="button" class="cart" id="cart"  value="장바구니"/>
                            <input type="button" class="order" value="구매하기"/>
                        </div>
                    </div>
                </article>

                <!-- 상품 정보 내용 -->
                <article class="detail">
                    <nav>
                        <h1>상품정보</h1>
                    </nav>
                    <!-- 상품상세페이지 이미지 -->
                    <img src="../file/${productView.cate1 }/${productView.cate2 }/${productView.detail }" width=860px height=auto alt="상세페이지1">
                   
                </article>


                <!-- 상품 정보 제공 고시 내용 -->
                <article class="notice">
                    <nav>
                        <h1>상품 정보 제공 고시</h1>
                        <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
                    </nav>
                    <table border="0">
                        <tr>
                            <td>상품번호</td>
                            <td>10110125435</td>
                        </tr>
                        <tr>
                            <td>상품상태</td>
                            <td>새상품</td>
                        </tr>
                        <tr>
                            <td>부가세 면세여부</td>
                            <td>과세상품</td>
                        </tr>
                        <tr>
                            <td>영수증발행</td>
                            <td>발행가능 - 신용카드 전표, 온라인 현금영수증</td>
                        </tr>
                        <tr>
                            <td>사업자구분</td>
                            <td>사업자 판매자</td>
                        </tr>
                        <tr>
                            <td>브랜드</td>
                            <td>블루포스</td>
                        </tr>
                        <tr>
                            <td>원산지</td>
                            <td>국내생산</td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                            <td>제품소재</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>색상</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>치수</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제조자/수입국</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제조국</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>취급시 주의사항</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>제조연월</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>품질보증기준</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>A/S 책임자와 전화번호</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                            <td>주문후 예상 배송기간</td>
                            <td>상세정보 직접입력</td>
                        </tr>
                        <tr>
                        <td colspan="2">구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우</td>
                        </tr>
                    </table>
                    <p class="notice">
                        소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라 청약철회를 하고
                        동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 반환하였음에도 불구 하고 결제 대금의
                        환급이 3영업일을 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조
                        제2항 및 동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 이율을 곱하여
                        산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증 및 결제대금의
                        환급신청은 [나의쇼핑정보]에서 하실 수 있으며, 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
                    </p>
                </article>
                
                <!-- 상품 리뷰 내용 -->
                <article class="review">
                    <nav>
                        <h1>상품리뷰</h1>
                    </nav>
                    <ul>
                        <li>
                            <div>
                                <h5 class="rating star4">상품평</h5>
                                <span>seo******	2018-07-10</span>
                            </div>
                            <h3>상품명1/BLUE/L</h3>
                            <p>
                                가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                                아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                                제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                            </p>
                        </li>

                    </ul>
                    <div class="paging">
                        <span class="prev">
                            <a href="#"><&nbsp;이전</a>
                        </span>
                        <span class="num">
                            <a href="#" class="on">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#">4</a>
                            <a href="#">5</a>
                            <a href="#">6</a>
                            <a href="#">7</a>
                        </span>
                        <span class="next">
                            <a href="#">다음&nbsp;></a>
                        </span>
                    </div>

                </article>
            </section>
        </main>
<jsp:include page="./footer.jsp"></jsp:include>