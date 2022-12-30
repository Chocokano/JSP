<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"></jsp:include>         
            <section id="admin-index">
                <nav>
                    <h3>관리자 메인</h3>
                    <p>
                        HOME > <strong>메인</strong>
                    </p>
                </nav>

                <h4>쇼핑몰 운영현황</h4>
                <article>
                    <table>	
                        <tr>
                            <td>
                                <strong>주문건수(건)</strong>
                                <span>131</span>
                            </td>
                            <td>
                                <strong>주문금액(원)</strong>
                                <span>1,130,000</span>
                            </td>
                            <td>
                                <strong>회원가입(명)</strong>
                                <span>1014</span>
                            </td>
                            <td>
                                <strong>쇼핑몰 방문(명)</strong>
                                <span>1014</span>
                            </td>
                            <td>
                                <strong>신규게시물(건)</strong>
                                <span>100</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>
                                    <span>PC</span>
                                    <span>60</span>
                                </p>
                                <p>
                                    <span>Mobile</span>
                                    <span>71</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>PC</span>
                                    <span>60</span>
                                </p>
                                <p>
                                    <span>Mobile</span>
                                    <span>71</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>PC</span>
                                    <span>60</span>
                                </p>
                                <p>
                                    <span>Mobile</span>
                                    <span>71</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>PC</span>
                                    <span>60</span>
                                </p>
                                <p>
                                    <span>Mobile</span>
                                    <span>71</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>PC</span>
                                    <span>60</span>
                                </p>
                                <p>
                                    <span>Mobile</span>
                                    <span>71</span>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>
                                    <span>어제</span>
                                    <span>4</span>
                                </p>
                                <p>
                                    <span>주간</span>
                                    <span>10</span>
                                </p>
                                <p>
                                    <span>월간</span>
                                    <span>30</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>어제</span>
                                    <span>4</span>
                                </p>
                                <p>
                                    <span>주간</span>
                                    <span>10</span>
                                </p>
                                <p>
                                    <span>월간</span>
                                    <span>30</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>어제</span>
                                    <span>4</span>
                                </p>
                                <p>
                                    <span>주간</span>
                                    <span>10</span>
                                </p>
                                <p>
                                    <span>월간</span>
                                    <span>30</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>어제</span>
                                    <span>4</span>
                                </p>
                                <p>
                                    <span>주간</span>
                                    <span>10</span>
                                </p>
                                <p>
                                    <span>월간</span>
                                    <span>30</span>
                                </p>
                            </td>
                            <td>
                                <p>
                                    <span>어제</span>
                                    <span>4</span>
                                </p>
                                <p>
                                    <span>주간</span>
                                    <span>10</span>
                                </p>
                                <p>
                                    <span>월간</span>
                                    <span>30</span>
                                </p>
                            </td>		
                        </tr>
                    </table>
                </article>

                <h4>주문 업무현황</h4>
                <article>
                    <table>
                        <tr>
                            <td>
                                <strong>입금대기(건)</strong>
                                <span>100</span>
                            </td>
                            <td>
                                <strong>배송준비(건)</strong>
                                <span>100</span>
                            </td>
                            <td>
                                <strong>취소요청(건)</strong>
                                <span>100</span>
                            </td>
                            <td>
                                <strong>교환요청(건)</strong>
                                <span>100</span>
                            </td>
                            <td>
                                <strong>반품요청(건)</strong>
                                <span>100</span>
                            </td>
                        </tr>
                    </table>                    
                </article>

                <div>
                    <div>
                        <h4>공지사항</h4>
                        <article>
               				 <c:set var="done_loop" value="false"/>
           					 <c:set var="i" value="1"></c:set>
                        	 <c:forEach var="artiNotice" items="${artiNotice}">
                        	 	 <c:if test="${done_loop ne true}">
                        	 	<p>
	                                <span>
	                                <a href="/kmarket/cs/notice/view.do?no=${artiNotice.no }&cate=${artiNotice.cate1}">	                                    	
	                                    	[공지] ${artiNotice.title }</a>
	                                </span>
	                                <span>${artiNotice.rdate.substring(2,16) }</span>
                            	</p>
                            	 <c:set var="i" value="${i+1}"></c:set>
								  	<c:if test="${i eq 6}">
			                       		<c:set var="done_loop" value="true"/>
			                    	</c:if>
			                    	</c:if>
                        	</c:forEach>
         
                        </article>
                    </div>

                    <div>
                        <h4>고객문의</h4>
                          <article>
               				 <c:set var="done_loop" value="false"/>
           					 <c:set var="i" value="1"></c:set>
                        	 <c:forEach var="artiQna" items="${artiQna}">
                        	 	 <c:if test="${done_loop ne true}">
                        	 	<p>
	                                <span>
	                                 <a href="/kmarket/cs/qna/view.do?no=${artiQna.no }&cate=${artiQna.cate1}">	                                    	
	                                    	[문의] ${artiQna.title }</a>
	                                
	                             </span>
	                                <span>${artiQna.rdate.substring(2,16) }</span>
                            	</p>
                            	 <c:set var="i" value="${i+1}"></c:set>
								  	<c:if test="${i eq 6}">
			                       		<c:set var="done_loop" value="true"/>
			                    	</c:if>
			                    	</c:if>
                        	</c:forEach>
         
                        </article>
                    </div>
                </div>
            </section>
        </main>
<jsp:include page="./_footer.jsp"></jsp:include>     
     