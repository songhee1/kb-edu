package com.service.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.spring.domain.User;
import com.service.spring.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
//	@RequestMapping("memberLogin.do")
//	public ModelAndView login(String userId, String password) throws Exception{
//		return null;
//	}
	
	@RequestMapping("memberLogin.do")
	public ModelAndView login(User user, HttpServletRequest request) throws Exception{
		/*
		 * login 로직 호출
		 * 1. userService의 login() 호출
		 * 2. 세션에 바인딩
		 * 3. 네비게이션
		*/
		
		User rvo = userService.login(user);
		System.out.println("rvo :: "+rvo);
		
		String path = "index.jsp";
		String msg = "아이디 혹은 패스워드가 틀렸습니다.";
		if(rvo != null) {//세션에 정보를 바인딩
			request.getSession().setAttribute("user", rvo);
			
			msg = "정상적으로 로그인 되었습니다";
		}
		request.getSession().setAttribute("msg", msg); //로그인 성공 여부에 따라 메세지가 달라지도록
		return new ModelAndView("redirect:"+path); //redirect:index.jsp
	}
	
	@RequestMapping("memberLogout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		// 로그아웃 로직 구현 = 세션을 죽이는 로직.. 네비게이션(index.jsp)
		session.invalidate();
		
		return new ModelAndView("redirect:index.jsp"); //redirect:index.jsp
	}
}
