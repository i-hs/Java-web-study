package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "", pwd = "";
		Boolean isLogOn = false;
		HttpSession session = request.getSession();
		if(session != null) {
			isLogOn = (Boolean) session.getAttribute("isLogOn");
			if(isLogOn==true) {
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pwd");
				out.print("<html><body>");
				out.print("아이디: "+id+"<br>");
				out.print("비밀번호: "+pwd+"<br>");
				out.print("</body></html>");
			}else{
				response.sendRedirect("login3.html");  // 로그인 상태가 아니면 로그인창으로 이동.
			}
		}else {
			response.sendRedirect("login3.html");  // 세션이 생성되지 않았으면 로그인창으로 이동.
		}
		
	}

}
