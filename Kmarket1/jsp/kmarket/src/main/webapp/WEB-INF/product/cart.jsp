<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="./header.jsp"></jsp:include>      
<script>

		//체크박스 이벤트
		$(document).ready(function() {
			
			$('input[name=all]').click(function() {
				if($(this).is(":checked")) $("input[name=check]").prop('checked', true);
				else $("input[name=check]").prop("checked", false);
			});
		
			$("input[name=check]").click(function() {
				
				var total = $("input[name=check]").length;
				var checked = $("input[name=check]:checked").length;
		
				if(total != checked) $('input[name=all]').prop("checked", false);
				else $('input[name=all]').prop("checked", true); 
			});
			
			//체크박스 삭제
			$("input[name=del]").click(function(){
				//alert('클릭확인');
				
				let checked = $('input[name=check]:checked').length;
				let arr = [];
				
				$('input[name=check]:checked').each(function(){
					arr.push($(this).val());
				});
				
				
				console.log("here1 : " + arr);
				
				if(checked == 0){
					alert('선택된 상품이 없습니다.');
					
				}else if(confirm('선택한 상품을 삭제하겠습니까?')){
					
					console.log("here2");	
					
					$.ajax({
						url : '/kmarket/product/DeleteCart.do',
						type : 'POST',					
						data : {'arr' : arr},
						traditional: true,
						dataType : 'json',
						success : function(data){
							if(data.result > 0){
								$('input[name=check]:checked').parents('tr').remove();
								location.reload();
							}
						}
					});
				}
			});
			
			//계산 필드
			$('input[class=checkprice]').click(function() {
				var total = 0;
				let checked = $('input[name=check]:checked').length;
				let arr = [];
				
				$('input[name=check]:checked').each(function(){
					arr.push($(this).val());
				});
				console.log("checkNo : " + arr);
				
				if(checked > 0){
				$.ajax({
					url : '/kmarket/product/CartPrice.do',
					type : 'POST',					
					data : {'arr' : arr},
					traditional: true,
					dataType : 'json',
					success : function(data){
						console.log("data : "+data.price);
						$('td[class=product_price]').empty("");
						$('td[class=product_price]').append(data.price.toLocaleString());
						$('td[class=product_delivery]').empty("");
						$('td[class=product_delivery]').append(data.delivery.toLocaleString());
						$('td[class=product_discount]').empty("");
						$('td[class=product_discount]').append(data.discount.toLocaleString());
						$('td[class=product_num]').empty("");
						$('td[class=product_num]').append(data.count.toLocaleString());
						$('td[class=product_point]').empty("");
						$('td[class=product_point]').append(data.point.toLocaleString());
						$('td[class=product_total]').empty("");
						$('td[class=product_total]').append(data.total.toLocaleString());
					}
				});

				
				}else {
					$('td[class=product_price]').empty("");
					$('td[class=product_price]').append("0");
					$('td[class=product_delivery]').empty("");
					$('td[class=product_delivery]').append("0");
					$('td[class=product_discount]').empty("");
					$('td[class=product_discount]').append("0");
					$('td[class=product_num]').empty("");
					$('td[class=product_num]').append("0");
					$('td[class=product_point]').empty("");
					$('td[class=product_point]').append("0");
					$('td[class=product_total]').empty("");
					$('td[class=product_total]').append("0");
				}
		  });
			
		$('input[name=order]').click(function(e){
			e.preventDefault();
			
			let arr = [];
			
			let checked = $('input[name=check]:checked').length;
			
			$('input[name=check]:checked').each(function(){
				arr.push($(this).val());
			});
			
			let jsonData = {
				"arr" : arr
			};
			
			if(checked == 0){
				alert('상품은 1개 이상 선택해주세요.');
				
			}else{
				$.ajax({
					url : '/kmarket/product/cart.do',
					type : 'POST',
					data : jsonData,
					traditional : true,
					dataType : 'json',
					success : function(data){
						if(data.result > 0){
							location.href='/kmarket/product/order.do';
						}
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
              	 		<li><a href="/kmarket/product/list.do?&cate1=${cate2List.cate1}&cate2=${cate2List.cate2}&cate=list_1&pg=1">${cate2List.c2Name }</a></li>            	 		
              	 	</c:if>          
                </c:forEach>
              </ol>
            </li>
            </c:forEach>

               </ul>
            </aside>

            <section class="cart">
                <nav>
                    <h1>장바구니</h1>
                    <p>HOME > <strong>장바구니</strong></p>
                </nav>

                <form action="/kmarket/product/cart.do">
                <input type="hidden" name="uid" value=${sessUser.uid }>
                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all" class="checkprice"></th>
                            <th>상품명</th>
                            <th>총수량</th>
                            <th>판매가</th>
                            <th>할인</th>
                            <th>포인트</th>
                            <th>배송비</th>
                            <th>소계</th>
                        </tr>
                        <c:choose>
                        <c:when test="${empty carts}">
                        <tr class="empty">
                            <td colspan="7">장바구니에 상품이 없습니다.</td>
                        </tr>
                        </c:when>
                        <c:otherwise>
                        <c:forEach var="cart" items="${carts}">
                        <tr>
                            <td><input type="checkbox" name="check" class="checkprice" value="${cart.cartNo }" ></td>
                            <td>
                                <article>
                                    <a href="#"><img src="/kmarket/file/${cart.cate1 }/${cart.cate2}/${cart.thumb1}" alt="썸네일"></a>
                                    <div>
                                        <h2><a href="/kmarket/product/view.do?proNo=${cart.proNo }&cate1=${cart.cate1}&cate2=${cart.cate2}">${cart.proName }</a></h2>
                                        <p>${cart.descript }</p>
                                    </div>
                                </article>
                            </td>
                            <td>${cart.count}</td>
                            <td><fmt:formatNumber value="${cart.price }" pattern="#,###원" /></td>
                            <td><fmt:formatNumber value="${(cart.discount)*(cart.price)/100}" pattern="#,###원" /></td>
                            <td>${cart.point }</td>
                            <td><fmt:formatNumber value="${cart.delivery }" pattern="#,###" /></td>
                            <td><fmt:formatNumber value="${cart.total }" pattern="#,###원" />
                            </td>
                        </tr>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                    </table>
                    <input type="button" name="del" value="선택삭제">

                    <!-- 장바구니 전체합계 -->
                    <div class="total">
                        <h2>전체합계</h2>
                        <table border="0">
                            <tr>
                                <td>상품수</td>
                                <td class="product_num">0</td>

                              </tr>
                              <tr>
                                <td>상품금액</td>
                                <td class="product_price">0</td>
                              </tr>
                              <tr>
                                <td>할인금액</td>
                                <td class="product_discount">0</td>

                              </tr>
                              <tr>
                                <td>배송비</td>
                                <td class="product_delivery">0</td>
                              </tr>              
                              <tr>
                                <td>포인트</td>
                                <td class="product_point">0</td>
                              </tr>
                              <tr>
                                <td>전체주문금액</td>
                                <td class="product_total">0</td>
                                

                              </tr>
                        </table>
                        <input type="submit" name="order" id="order" value="주문하기">
                    </div>
                </form>
            </section>
        </main>
<jsp:include page="./footer.jsp"></jsp:include>