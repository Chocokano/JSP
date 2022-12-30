<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../cs_header.jsp"/>
        <section id="cs">
            <div class="qna">
                <nav><div><p>홈<span>></span>문의하기</p></div></nav>
                <section class="list">
                    <aside><h2>문의하기</h2>

            			<ul>
                             <li class="${cate eq '10' ? 'on' : 'off'}"><a href="./list.do?&cate=10&pg=1">회원</a></li>
                             <li class="${cate eq '11' ? 'on' : 'off'}"><a href="./list.do?&cate=11&pg=1">쿠폰/혜택/이벤트</a></li>
                             <li class="${cate eq '12' ? 'on' : 'off'}"><a href="./list.do?&cate=12&pg=1">주문/결제</a></li>
                             <li class="${cate eq '13' ? 'on' : 'off'}"><a href="./list.do?&cate=13&pg=1">배송</a></li>
                             <li class="${cate eq '14' ? 'on' : 'off'}"><a href="./list.do?&cate=14&pg=1">취소/반품/교환</a></li>
                             <li class="${cate eq '15' ? 'on' : 'off'}"><a href="./list.do?&cate=15&pg=1">여행/숙박/항공</a></li>
                             <li class="${cate eq '16' ? 'on' : 'off'}"><a href="./list.do?&cate=16&pg=1">안전거래</a></li>
                        </ul>

                    </aside>
                    <article>
                        <nav>
                         <c:forEach var="cate1s" items="${cate1s }">
                        <c:if test="${cate1s.cate1 eq cate }">
                        	<h1>${cate1s.c1Name }</h1>
                        </c:if> 
                        </c:forEach>
                            <h2>문의 내용입니다.</h2>
                        </nav>
                        <table>
                            <tbody>
							                            	
                          <c:forEach var="ArticleQnaList" items="${ArticleQnaList}">
                          	<c:forEach var="cate2s" items="${cate2s}">
                          		<c:if test="${cate2s.cate2 eq ArticleQnaList.cate2 }">
                          			<tr><td><a href="./view.do?&no=${ArticleQnaList.no }&cate=${cate}">[${cate2s.c2Name}] ${ArticleQnaList.title }</a></td><td>${fn:substring(ArticleQnaList.uid, 0, fn:length(ArticleQnaList.uid) - 3)}***</td><td>${ArticleQnaList.rdate}</td></tr>
                          		</c:if>
                        	</c:forEach>
                        </c:forEach>
                         

                            </tbody>
                        </table>
                       
                        <div class="page">
                           	<c:if test="${pageGroupStart gt 1}">
                        		 <a href="/kmarket/cs/qna/list.do?cate=${cate }&pg=${pageGroupStart-1}" class="prev">이전</a>
                        	</c:if>
                            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                            	<a href="/kmarket/cs/qna/list.do?cate=${cate }&pg=${i}" class="num ${currentPage eq i? 'current':'off'}">${i}</a>
                            </c:forEach>
                            <c:if test="${pageGroupStart lt lastPageNum}">
                            	<a href="/kmarket/cs/qna/list.do?cate=${cate }&pg=${pageGroupStart+1}" class="next">다음</a>
                            </c:if>
                        </div>
                        <c:if test="${sessUser.uid ne null }">
                        <a href="./write.do" class="btnWrite">문의하기</a>
                        </c:if>
                    </article>
                </section>
            </div>
        </section>
 <jsp:include page="../cs_footer.jsp"/>