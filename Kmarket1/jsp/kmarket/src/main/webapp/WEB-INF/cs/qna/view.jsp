<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../cs_header.jsp"/>
        <section id="cs">
            <div class="qna">
                <nav><div><p>홈<span>></span>문의하기</p></div></nav>
                <section class="view">
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
                           <c:forEach var="cate2s" items="${cate2s }">
                           		<c:if test="${cate2s.cate2 eq QnaView.cate2 }">
                           			<h2 class="title">[${cate2s.c2Name }] ${QnaView.title} </h2>
                           		</c:if>
                           </c:forEach>
                            
                            <span>
                            ${fn:substring(QnaView.uid, 0, fn:length(QnaView.uid) - 3)}***
                            </span>
                            <span>${QnaView.rdate } </span>
                        </nav>
                       <div class="content">
                        <p>
                         
                          ${QnaView.content }
                           
                        </p>
                       </div>
                       <a href="../qna/list.do?&cate=${cate }" class="btnList">목록보기</a>
                    </article>
                </section>
            </div>
        </section>
     <jsp:include page="../cs_footer.jsp"/>