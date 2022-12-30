<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../_header.jsp"></jsp:include>

            <section id="admin-product-list">
                <nav>
                    <h3>공지사항 쓰기</h3>
                    <p>
                        Home > 고객센터 > <strong>공지사항</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->
                <section>
                
        			  <article>
                      <form action="/kmarket/admin/cs/NoticeWrite.do" method="post">
                      <input type="hidden" name="uid" value="${sessUser.uid}"/>
                        <table>
                            <tbody>
                                <tr>
                                    <td>유형</td>
                                    <td>
                                      <select name="category1">
                                            <option value="">선택</option>
                                            <c:forEach var="cate" items="${cate1s }">
                                             	<option name="cate1" value="${cate.cate1 }">${cate.c1Name }</option>
                                            </c:forEach> 
                                        </select>

                                    </td>
                                </tr>
                                <tr>
                                    <td>제목</td>
                                    <td><input type="text" name="noticeTitle"  class="noticeTitle" placeholder="제목을 입력하세요."></td>
                                </tr>
                                <tr>
                                    <td>내용</td>
                                    <td><textarea name="content" class="noticeText" placeholder="내용을 입력하세요."></textarea></td>
                                </tr>
                            </tbody>
                        </table>
                        <div>
                            <a href="./list.do" class=noticeCancle>취소하기</a>
                            <input type="submit" class="noticeSubmit" value="등록하기">
                        </div>
                      </form>
                    </article>

                </section>


                <p class="next">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
                
                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
         </main>
<jsp:include page="../_footer.jsp"></jsp:include>