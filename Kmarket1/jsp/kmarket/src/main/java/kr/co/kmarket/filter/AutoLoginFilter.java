package kr.co.kmarket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.vo.MemberVO;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		logger.info("AutoLoginFilter...");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession sess = req.getSession();
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			
			for(Cookie cookie : cookies) {
				
				if(cookie.getName().equals("SESSID")) {
					
					String sessId = cookie.getValue();					
					MemberVO vo = MemberDAO.getInstance().selectMemberBySessId(sessId);
					
					if(vo != null) {
						// 로그인 처리
						sess.setAttribute("sessUser", vo);						
					}
				}
			}
		}
		
		// 다음 필터로 이동 
		chain.doFilter(request, response);
	}
}