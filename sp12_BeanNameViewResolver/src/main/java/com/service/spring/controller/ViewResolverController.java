package com.service.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewResolverController {

	//service wired
	
	@RequestMapping("findBoard.do")
	public String findBoard() {
		System.out.println("service call....데이터 리턴...바인딩");
		return "board_result";//위치정보, 확장자 정보가 없다. viewResolver에 저장했었는데->설정문서에 가서 확인
	}
	
	//board_result로 요청이 가면 presentation bean에서 정의한  0순위 -> 1순위 순으로 찾는다.
	
	@RequestMapping("findProduct.do")
	public String findProduct() {
		System.out.println("service call....데이터 리턴...바인딩");
		return "product_result";
	}
	@RequestMapping("register.do")
	public String register() {
		System.out.println("service call....데이터 리턴...바인딩");
		return "register_result";
	}
	//register_result는 0순위->1순위 빈을 찾아서 해당하는 페이지로 이동하는데, 0순위에는 해당하는 
}
