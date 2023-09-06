package com.service.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.spring.domain.MemberVO;
import com.service.spring.model.MemberDAO;
import com.service.spring.model.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	
	@Autowired
	private MemberDAO memberDAO;
	//서비스와 dao를 각각 다르게 만들면(메서드가 다르면), 
	
	@RequestMapping("find.do")
	public String find(Model model, String id) throws Exception{
		MemberVO rvo = null;
		String path = "find_fail";
		
		try {
			rvo = memberService.getMember(id);
			if(rvo != null) {
				model.addAttribute("vo", rvo);
				model.addAttribute("msg", "회원 발견 성공");
				path = "find_ok";
				return path;
			} else {
				model.addAttribute("msg", "회원 발견 실패");
				path = "find_fail";
				return path;
			}
		}catch(Exception e) {
			return path;
		}
	}
	
	@RequestMapping("login.do")
	public String login (Model model, MemberVO pvo, HttpSession session) {
		String path = "login";
		MemberVO rvo = null;
		try {
			rvo = memberService.login(pvo);
			if(rvo != null) {
				model.addAttribute("vo", rvo);
				model.addAttribute("msg", "회원 로그인 성공");
				path = "login_result";
				session.setAttribute("vo", rvo);
				return path;
			} else {
				model.addAttribute("msg", "회원 로그인 실패");
				return "redirect:/index.jsp";
			}
		}catch(Exception e) {
			return path;
		}
	}
	
	@RequestMapping("showAll.do")
	public String showAll(Model model) {
		
		String path = "find_fail";
		List<MemberVO> list = null;
		
		try {
			list = memberService.showAllMember();
			path = "allView";
			model.addAttribute("msg", "회원 전체 조회 성공");
			model.addAttribute("list", list);
		}catch(Exception e) {
			model.addAttribute("msg", "회원 전체 조회 실패");
		}
		return path;
	}
	
	@RequestMapping("update.do")
	public String update(Model model, MemberVO pvo, HttpSession session) {
		
		String path = "find_fail";
		
		
		try {
			memberDAO.updateMember(pvo);
			path = "update_result";
			
			model.addAttribute("msg", "회원 수정 성공");
			session.setAttribute("vo", pvo);
			
		}catch(Exception e) {
			model.addAttribute("msg", "회원 수정 실패");
		}
		return path;
	}
	
	@RequestMapping("logout.do")
	public String update(HttpSession session) {
		
		String path = "logout";
		
		try {
			if(session.getAttribute("vo") != null) {
				session.invalidate();
			}
		}catch(Exception e) {
			
		}
//		return "redirect:logout.jsp";
		return path;
	}
	
	@RequestMapping("register.do")
	public String register(Model model, MemberVO pvo) {
		
		String path = "register_fail";
		
		try {
			memberDAO.registerMember(pvo);
			path = "register_result";
			model.addAttribute("msg", "회원 등록 성공");
			model.addAttribute("vo", pvo);
			return "redirect:/showAll.do";
			
		}catch(Exception e) {
			model.addAttribute("msg", "회원 등록 실패");
			return path;
		}
	}
	
	@RequestMapping("idExist.do")
	public String idExist(String id, Model model) throws Exception {
		boolean check = false;
		String rid = memberDAO.idExist(id);
		
		if(rid != null) {
			check = true;
		}
		
		model.addAttribute("check", check);
		
		return "JsonView";
	}
}









