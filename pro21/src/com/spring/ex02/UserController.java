package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController
{
	public ModelAndView login(HttpServletRequest request, 
								HttpServletResponse response) throws Exception{
		String userID="";
		String userPwd="";
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		userID = request.getParameter("userID");
		userPwd = request.getParameter("userPwd");
		mav.addObject("userID", userID);
		mav.addObject("userPwd", userPwd);
		mav.setViewName("result");  // 포워딩할 jsp 이름
		return mav;
	}
	
	public ModelAndView memberInfo(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);
		mav.setViewName("memberInfo");  // 포워딩할 jsp 이름 
		return mav;
		
	}

}
