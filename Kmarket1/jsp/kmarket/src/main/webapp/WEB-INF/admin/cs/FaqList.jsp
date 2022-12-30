<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../_header.jsp"></jsp:include>

            <section id="admin-product-list">
                <nav>
                    <h3>자주묻는질문 목록</h3>
                    <p>
                        Home > 고객센터 > <strong>자주묻는질문</strong>
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

                    </table>
                    

                    <input type="button" value="선택삭제" />

                </section>


                <p class="next">
                    <strong>Tip!</strong>
                    전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
                </p>
                
                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
         </main>
<jsp:include page="../_footer.jsp"></jsp:include>