<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="./cs_header.jsp"/>
 
        <section id="cs">
            <div class="main">
                <h1 class="title">
                    <strong>케이마켓</strong>이 도와드릴게요!
                </h1>
                <section class="notice">
                    <h1>공지사항
                        <a href="./notice/list.do?cate=10&pg=1">전체보기</a>
                    </h1>
                    <ul>
                	<c:set var="done_loop" value="false"/>
           				 <c:set var="i" value="1"></c:set>
                       <c:forEach var="NoticeList" items="${NoticeList}">      
                          <c:if test="${done_loop ne true}">
                         		<c:forEach var="NoticeCate" items="${NoticeCate}">
                         		<c:if test="${NoticeCate.cate1 eq NoticeList.cate1 }">
                           		         <li>
	                           		         <a href="./notice/view.do?no=${NoticeList.no }&cate=${NoticeList.cate1}" class="title">[${NoticeCate.c1Name }] ${NoticeList.title }</a>
	                           		         <p><span class="date">${NoticeList.rdate.substring(2,11) }</span></p>

                           		         </li>
                           		           </c:if>
                          		</c:forEach>
                          		 <c:set var="i" value="${i+1}"></c:set>
					  	<c:if test="${i eq 6}">
                       		<c:set var="done_loop" value="true"/>
                    	</c:if>
                    	</c:if>
                       </c:forEach>
                                   		
      					</ul>
                </section>
                <section class="faq">
                    <h1>자주 묻는 질문
                        <a href="./faq/list.do?cate=10">전체보기</a>
                    </h1>
                    <ol>
                        <li><a href="./faq/list.do?&cate=10"><span>회원</span></a></li>
                        <li><a href="./faq/list.do?&cate=11"><span>쿠폰/이벤트</span></a></li>
                        <li><a href="./faq/list.do?&cate=12"><span>주문/결제</span></a></li>
                        <li><a href="./faq/list.do?&cate=13"><span>배송</span></a></li>
                        <li><a href="./faq/list.do?&cate=14"><span>취소/반품/교환</span></a></li>
                        <li><a href="./faq/list.do?&cate=15"><span>여행/숙박/항공</span></a></li>
                        <li><a href="./faq/list.do?&cate=16"><span>안전거래</span></a></li>
                    </ol>
                </section>
                <section class="qna">
                    <h1>문의하기
                        <a href="./qna/list.do?cate=10&pg=1">전체보기</a>
                    </h1>
                    <ul>
             
					  	<c:set var="done_loop" value="false"/>
           				 <c:set var="i" value="1"></c:set>
					   <c:forEach var="CsQnaList" items="${CsQnaList}">
					    <c:if test="${done_loop ne true}">
							      	<c:forEach var="cate2s" items="${cate2s}">
						      			<c:if test="${cate2s.cate2 eq CsQnaList.cate2 and cate2s.cate1 eq CsQnaList.cate1}">
										    <li><a href="./qna/view.do?&no=${CsQnaList.no }&cate=${CsQnaList.cate1}">
										    [ ${cate2s.c2Name } ] ${CsQnaList.title }</a><p>
										    <span class="uid">
										    ${fn:substring(CsQnaList.uid, 0, fn:length(CsQnaList.uid) - 3)}***
										    </span><span class="date">${CsQnaList.rdate.substring(2,11) }</span></p></li>
										</c:if>			
									</c:forEach>				    		    
					    <c:set var="i" value="${i+1}"></c:set>
					  	<c:if test="${i eq 6}">
                       		<c:set var="done_loop" value="true"/>
                    	</c:if>
                    	</c:if>
                    	
					   </c:forEach>
                    </ul>
                    <a href="./qna/write.do" class="ask">문의글 작성 ></a>
                </section>
                <section class="tel">
                    <h1 class="tel">1:1 상담이 필요하신가요?</h1>
                    <article>
                        <div><h3>고객센터 이용안내</h3>
                            <p><span>일반회원/비회원</span><br>
                                <strong>1566-0001</strong>
                                <span>(평일 09:00 ~ 18:00)</span>
                            </p>
                            <p><span>스마일클럽 전용</span><br>
                                <strong>1566-0002</strong>
                                <span>(365일 09:00 ~ 18:00)</span>
                            </p>
                        </div>
                    </article>
                    <article>
                        <div>
                        <h3>판매상담 이용안내</h3>
                        <p><span>판매고객</span><br>
                            <strong>1566-5700</strong>
                            <span>(평일 09:00 ~ 18:00)</span>
                        </p>
                        <p><a href="#">판매자 가입 및 서류 접수 안내</a></p>
                    </div>
                    </article>
                </section>
            </div>
        </section>
       <jsp:include page="./cs_footer.jsp"/>