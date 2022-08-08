package com.bit.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//17강.

@Controller
public class TempControllerTest {

	//  http://localhost:8000/blog/temp/home
	@GetMapping("temp/home")
	 public String tempHome() {
		System.out.println("tempHome()");
		//@Controller가 붙을 때, public String tempHome() 는 리턴으로 파일을 리턴한다.
		//스프링의 파일 리턴 기본 경로: src/main/resources/static 이며, static에는 정적파일만 넣어야. .jsp는 안됨.
		//밑 리턴명은 return "/home.html"; 돼야.
		//그래야 src/main/resources/static/home.html를 찾을 수 있다.
		 return "/home.html";
	 }
	
	
	@GetMapping("temp/jsp")
	public String jspTest() {
		System.out.println("jsptest"	);
		// 동적페이지 경로: /WEB-INF/view/파일명.jsp
		return "test";
	}
	
	
}














