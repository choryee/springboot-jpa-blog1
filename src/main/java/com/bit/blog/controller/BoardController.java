package com.bit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bit.blog.config.auth.PrincipalDetail;
import com.bit.blog.service.BoardService;

@Controller
// @Controller는 @RestController와 달리, viewResoler가 작동해 리턴의 페이지로 모델에 넣은 정보를 보내준다. 54강
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3 , sort = "id",
			direction = Sort.Direction.DESC) Pageable pageable) { //55강.페이징처리부분만
		model.addAttribute("boards", boardService.글목록(pageable));
		//위 index.jsp(메인페이지)로 갈때는 "boards"라는 키에 boardService.글목록()를 들고 가야. 54강.
		
	//public String index(@AuthenticationPrincipal PrincipalDetail principal) { //컨트롤러가 세션은 어떻게 찾는가. 52강.
	
		//WEB-INF/views/index.jsp 됨. 아래가
		//System.out.println("로그인 사용자 아이디 : "+principal.getUsername());
		return "index";
	}
	
	
	//USER권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
		
	
	
}













