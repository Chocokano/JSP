<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../_header.jsp"></jsp:include>

            <section id="admin-product-list">
                <nav>
                    <h3>상품목록</h3>
                    <p>
                        Home > 상품관리 > <strong>상품목록</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->
                <section>
                    <div>
                        <select name="search">
                            <option value="search1">상품명</option>
                            <option value="search1">상품코드</option>
                            <option value="search1">제조사</option>
                            <option value="search1">판매자</option>
                        </select>
                        <input type="text" name="search">
                    </div>
                    <table>
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>이미지</th>
                            <th>상품코드</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>할인율</th>
                            <th>포인트</th>
                            <th>재고</th>
                            <th>판매자</th>
                            <th>조회</th>
                            <th>관리</th>
                        </tr>
						
						 <c:forEach var="listProduct" items="${listProduct}">
		                        <tr>
		                            <td><input type="checkbox" name="상품코드"/></td>
		                            <td><img src="/kmarket/file/${listProduct.cate1 }/${listProduct.cate2}/${listProduct.thumb1}" class="thumb"></td>
		                            <td>${listProduct.proNo }</td>
		                            <td>${listProduct.proName }</td>
		                            <td>${listProduct.price }</td>
		                            <td>${listProduct.discount }</td>
		                            <td>${listProduct.point }</td>
		                            <td>${listProduct.stock }</td>
		                            <td>${listProduct.seller }</td>
		                            <td>${listProduct.hit }</td>
		                            <td>
		                                <a href="#">[삭제]</a>
		                                <a href="#">[수정]</a>
		                            </td>
		                        </tr>
                        </c:forEach>

                    </table>
                    

                    <input type="button" value="선택삭제" />


        <div class="paging">
	<c:if test="${pageGroupStart > 1 }">
	<span class="prev">
		<a href="/kmarket/admin/product/list.do?uid=${sessUser.uid }&pg=${pageGroupStart-1}" ><&nbsp;이전</a>
	</span>
	</c:if>	
	
	<c:forEach var="num" begin="${pageGroupStart }" end="${pageGroupEnd }" step="1"> 
		<span class="num">
            		<a href="/kmarket/admin/product/list.do?uid=${sessUser.uid }&pg=${num}" class="${num eq currentPage ? 'on':'off' }">${num}</a>
		  </span>
	</c:forEach>    
	<c:if test="${pageGroupEnd < lastPageNum }">
		<span class="next">
			<a href="/kmarket/admin/product/list.do?uid=${sessUser.uid }&pg=${pageGroupEnd+1}" >다음 ></a>
		 </span>
	</c:if>

</div>
                    
                    
                    

                </section>


                <p class="next">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
                
                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
         </main>
<jsp:include page="../_footer.jsp"></jsp:include>