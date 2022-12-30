<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../cs_header.jsp"/>
        <section id="cs">
            <div class="notice">
                <nav><div><p>홈<span>></span>공지사항</p></div></nav>
                <section class="view">
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
                        <nav>
                        <c:forEach var="NoticeCate" items="${NoticeCate}">
                                 <c:if test="${NoticeCate.cate1 eq NoticeView.cate1 }">
                            <h2 class="title">[${NoticeCate.c1Name} ] ${NoticeView.title }</h2>
                            </c:if>
                            </c:forEach>
                            <span class="date">${NoticeView.rdate}</span>
                        </nav>
                       <div class="content">
                        <p>         
                           ${NoticeView.content}  
                                              
                        </p>
                       </div>
                       <a href="../notice/list.do" class="btnList">목록보기</a>
                    </article>
                </section>
            </div>
        </section>
<jsp:include page="../cs_footer.jsp"/>