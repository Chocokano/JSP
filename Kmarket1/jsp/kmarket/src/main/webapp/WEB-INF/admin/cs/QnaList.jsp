<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../_header.jsp"></jsp:include>

            <section id="admin-product-list">
                <nav>
                    <h3>문의하기 목록</h3>
                    <p>
                        Home > 공지사항 > <strong>문의하기</strong>
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
                            <th>번호</th>
                            <th>유형</th>
                            <th>제목</th>
                            <th>조회</th>
                            <th>날짜</th>
                            <th>관리</th>
                        </tr>
                        
                         <c:forEach var="ArticleQnaList" items="${ArticleQnaList}">
                        	 <c:forEach var="cate1s" items="${cate1s}">
                        	 <c:if test="${cate1s.cate1 eq ArticleQnaList.cate1 }">
	                        	 	<c:forEach var="cate2s" items="${cate2s}">		
	                         			<c:if test="${cate2s.cate2 eq ArticleQnaList.cate2 and cate2s.cate1 eq ArticleQnaList.cate1}">
	                         		
				                        <tr>
				                            <td><input type="checkbox" name="상품코드"/></td>
				                            <td>${ArticleQnaList.no }</td>
				                            <td>${cate2s.c2Name }</td>
				                            <td>${ArticleQnaList.title }</td>
				                            <td>${ArticleQnaList.hit }</td>
				                            <td>${ArticleQnaList.rdate.substring(2, 10) }</td>
				                            <td>
				                                <a href="#">[삭제]</a><br/>
				                                <a href="#">[수정]</a>
				                            </td>
				                        </tr>

			                        	</c:if>
			                        </c:forEach>
		                        </c:if>
                        	</c:forEach>
                        </c:forEach>

                    </table>
                    

                    <input type="button" value="선택삭제" />
                    
        <div class="paging">
	<c:if test="${pageGroupStart > 1 }">
	<span class="prev">
		<a href="/kmarket/admin/cs/QnaList.do?uid=${sessUser.uid }&pg=${pageGroupStart-1}" ><&nbsp;이전</a>
	</span>
	</c:if>	
	
	<c:forEach var="num" begin="${pageGroupStart }" end="${pageGroupEnd }" step="1"> 
		<span class="num">
            		<a href="/kmarket/admin/cs/QnaList.do?uid=${sessUser.uid }&pg=${num}" class="${num eq currentPage ? 'on':'off' }">${num}</a>
		  </span>
	</c:forEach>    
	<c:if test="${pageGroupEnd < lastPageNum }">
		<span class="next">
			<a href="/kmarket/admin/cs/QnaList.do?uid=${sessUser.uid }&pg=${pageGroupEnd+1}" >다음 ></a>
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