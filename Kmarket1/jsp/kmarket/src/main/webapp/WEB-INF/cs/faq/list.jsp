<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../cs_header.jsp"/>
<script>
$(function(){
       $('.select > li').mouseover(function(){
           $(this).css('background','#fff7f2');
       });
       $('.list li').mouseout(function(){
           $(this).css('background','white');
       });
   	
       $(document).ready(function(){
    var main = $('.main');


    $(".more").click(function(){
        if($(".select > li:nth-child(n+4)").is(":visible")){
            $(".select > li:nth-child(n+4)").slideDown(300);
        }

    })
       
});


</script>

        <section id="cs">
            <div class="faq">
                <nav><div><p>홈<span>></span>자주묻는 질문</p></div></nav>
                <section class="list">
                    <aside><h2>자주묻는 질문</h2>
                        <ul>
                         <li class="${cate eq '10' ? 'on' : 'off'}"><a href="./list.do?&cate=10">회원</a></li>
                             <li class="${cate eq '11' ? 'on' : 'off'}"><a href="./list.do?&cate=11">쿠폰/혜택/이벤트</a></li>
                             <li class="${cate eq '12' ? 'on' : 'off'}"><a href="./list.do?&cate=12">주문/결제</a></li>
                             <li class="${cate eq '13' ? 'on' : 'off'}"><a href="./list.do?&cate=13">배송</a></li>
                             <li class="${cate eq '14' ? 'on' : 'off'}"><a href="./list.do?&cate=14">취소/반품/교환</a></li>
                             <li class="${cate eq '15' ? 'on' : 'off'}"><a href="./list.do?&cate=15">여행/숙박/항공</a></li>
                             <li class="${cate eq '16' ? 'on' : 'off'}"><a href="./list.do?&cate=16">안전거래</a></li>
                            
                        </ul>
                    </aside>
                    <article>
                        <nav><h1>회원</h1>
                            <h2>가장 자주 묻는 질문입니다.</h2>
                        </nav>
                        <div>
                            <h3>가입</h3>
                            <ul class="select">
                                <li><a href="./view.do" class="aa"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.do"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.do"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.do"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.do"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li class="more"><a href="#">더보기</a></li>
                            </ul>
                        </div>
                        <div>
                            <h3>탈퇴</h3>
                            <ul class="select">
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li class="more"><a href="#">더보기</a></li>
                            </ul>
                        </div>
                        <div>
                            <h3>회원정보</h3>
                            <ul class="select">
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li class="more"><a href="#">더보기</a></li>
                            </ul>
                        </div>
                        <div>
                            <h3>로그인</h3>
                            <ul class="select">
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li><a href="./view.html"><span>Q.</span>개인회원과 번인회원에 차이가 있나요?</a></li>
                                <li class="more"><a href="#">더보기</a></li>
                            </ul>
                        </div>
                    </article>
                </section>
            </div>
        </section>
<jsp:include page="../cs_footer.jsp"/>
     