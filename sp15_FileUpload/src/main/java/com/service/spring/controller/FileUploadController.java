package com.service.spring.controller;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.service.spring.domain.UploadDataVO;

@Controller
public class FileUploadController {
	@RequestMapping("fileupload.do")
	public String upload(UploadDataVO vo, Model model, HttpServletRequest request) throws Exception{
		
		MultipartFile mFile = vo.getUploadFile();
		System.out.println("MultipartFile..."+mFile);
		
		//MultipartFile 주요기능
		if(mFile.isEmpty() != true) { // 업로드된 파일이 있다면
			System.out.println("파일의 사이즈: " + mFile.getSize());
			System.out.println("업로드한 파일 이름: " + mFile.getOriginalFilename());
			System.out.println("mFile.getName() : " + mFile.getName());
		}
		
		
		//2. 업로드한 파일을 Tomcat 서버로 배포(저장)
		String root = request.getSession().getServletContext().getRealPath("/"); 
		// request.getSession().getServletContext() : 서버가 가동되자마자 servletcontext가 생성되는데 그걸 가져오는 함수
		// .getRealPath("/"); : Context path 경로
		
		System.out.println("root : " + root); //context path 경로 확보
		String path = root + "upload\\"; // 뒤에 \\를 해주어야 파일이 붙는다
		
		System.out.println(path);
		
		//3. 업로드 파일의 카피본을 해당 경로 \ upload에 저장
		File copyFile = new File(path+mFile.getOriginalFilename());
		mFile.transferTo(copyFile);
		
		model.addAttribute("uploadfile", mFile.getOriginalFilename());
		
		return "upload_result";
	}
	
	@RequestMapping("filedown.do")
	public String down(String filename, Model model, HttpSession session) throws Exception{
		String root = session.getServletContext().getRealPath("/"); 
		String path = root + "upload\\"; // 뒤에 \\를 해주어야 파일이 붙는다
		
		model.addAttribute("path", path);
		
		return "downloadView";
	}
}
