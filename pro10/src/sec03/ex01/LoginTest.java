package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest
 */
//@WebServlet("/login")
public class LoginTest extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_name = request.getParameter("user_name");
		String user_pwd = request.getParameter("user_pwd");
		out.println("<html><body>");
		out.println("이름은 "+user_name+"<br>");
		out.println("비밀번호는 "+user_pwd+"<br>");
		out.println("</body></html>");
		
	}

}
