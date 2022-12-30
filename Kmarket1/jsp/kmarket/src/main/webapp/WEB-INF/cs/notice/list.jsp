<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../cs_header.jsp"/>
        <section id="cs">
            <div class="notice">
                <nav><div><p>홈<span>></span>공지사항</p></div></nav>
                <section class="list">
                    <aside><h2>공지사항</h2>
                        <ul>              
			               	<li class="${cate eq '10' ? 'on' : 'off'}"><a href="./list.do?&cate=10">전체</a></li>
			               	<li class="${cate eq '11' ? 'on' : 'off'}"><a href="./list.do?&cate=11">고객서비스</a></li>
			               	<li class="${cate eq '12' ? 'on' : 'off'}"><a href="./list.do?&cate=12">안전거래</a></li>
			               	<li class="${cate eq '13' ? 'on' : 'off'}"><a href="./list.do?&cate=13">위해상품</a></li>
			               	<li class="${cate eq '14' ? 'on' : 'off'}"><a href="./list.do?&cate=14">이벤트당첨</a></li>
                        </ul>
                    </aside>
                    <article>
                        <nav><h1>전체</h1>
                            <h2>공지사항 전체 내용입니다.</h2>
                        </nav>
                        <table>
                            <tbody>
                                 
                                   <c:forEach var="NoticeList" items="${NoticeList}">
                                   		<c:forEach var="NoticeCate" items="${NoticeCate}">
                                   			<c:if test="${NoticeCate.cate1 eq NoticeList.cate1 }">
	                                    	<tr><td><a href="./view.do?no=${NoticeList.no }&cate=${NoticeList.cate1}">	                                    	
	                                    	[${NoticeCate.c1Name} ] ${NoticeList.title }</a></td>
	                                    		<td>${NoticeList.rdate.substring(5,16) }</td>
	                                		</tr>
											</c:if>
                                   		</c:forEach>
                                   </c:forEach>
                        
                            </tbody>
                        </table>
            
                        
                        
                        
                         <div class="page">
                           	<c:if test="${pageGroupStart gt 1}">
                        		 <a href="/kmarket/cs/notice/list.do?cate=${cate }&pg=${pageGroupStart-1}" class="prev">이전</a>
                        	</c:if>
                            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                            	<a href="/kmarket/cs/notice/list.do?cate=${cate }&pg=${i}" class="num ${currentPage eq i? 'current':'off'}">${i}</a>
                            </c:forEach>
                            <c:if test="${pageGroupStart lt lastPageNum}">
                            	<a href="/kmarket/cs/notice/list.do?cate=${cate }&pg=${pageGroupStart+1}" class="next">다음</a>
                            </c:if>
                        </div>
                    </article>
                </section>
            </div>
        </section>
     <jsp:include page="../cs_footer.jsp"/>