package com.service.spring.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.spring.domain.MyProduct;
import com.service.spring.service.MyProductService;

@Controller 
//implements Controller 안하고 포조로 만든다
public class MyProductController {
	@Autowired
	private MyProductService myProductService;
	
	@RequestMapping("myProduct.do")
	public ModelAndView insert(MyProduct pvo) throws Exception{
		// request.getParameter 이렇게 세번 안받아도 된다. vo로 받을 수 있다.
		System.out.println("addProduct 호출 전 pvo ::: " + pvo); // 0, 세탁기, 대우, 50 이렇게 들어갈거다
		
		myProductService.addProduct(pvo);
		System.out.println("addProduct 호출 후 pvo ::: " + pvo); // 시퀀스 값이 꽂혀지는 것을 알 수 있다.
		
		
		return new ModelAndView("insert_result", "vo", pvo); // 바인딩하는 객체명은 vo로 할 것
	}
	
	@RequestMapping("/myProductSearch.do")
    public ModelAndView find(String command, String word) throws Exception{
		//자동 바인딩 command에 대해서 값들이 바인딩된다.
        List<MyProduct> list = null;
        String viewName = "";
        if(command.equals("findProductByName")) {
            list=myProductService.findProductByName(word);
            viewName = "find_result";
        }else if(command.equals("findProductByMaker")) {
            list=myProductService.findProductByMaker(word);
            viewName = "find_result";
        }else if(command.equals("findProducts")) {
            list=myProductService.findProducts();
            viewName = "find_result";
        }
        return new ModelAndView(viewName,"list",list);

    }
}
