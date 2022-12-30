package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.Cate1VO;
import kr.co.kmarket.vo.Cate2VO;

@WebServlet("/admin/product/register.do")
public class AdminRegisterController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//cate1
		List<Cate1VO> cate1s = service.SelectAdminProduct1();
		
		req.setAttribute("cate1s", cate1s);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/register.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//cate2 파라미터 수신
		String cate1 = req.getParameter("cate1");
		
		//cate2
		List<Cate2VO> cate2s = service.SelectAdminProduct2(cate1);
		req.setAttribute("cate2", cate2s);
		
		//json 출력
		Gson gson = new Gson();
		String jsonData = gson.toJson(cate2s);
		
		//인코딩 필터
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData.toString());
		

	}
}
