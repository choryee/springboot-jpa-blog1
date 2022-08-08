package com.bit.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.blog.config.auth.PrincipalDetail;
import com.bit.blog.dto.ResponseDto;
import com.bit.blog.model.Board;
import com.bit.blog.model.RoleType;
import com.bit.blog.model.User;
import com.bit.blog.repository.UserRepository;
import com.bit.blog.service.BoardService;
import com.bit.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


//53강

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	//@PostMapping("api/user")
	@PostMapping("/api/board") //53강
	 public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
	    boardService.글쓰기(board, principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}	  
		  
		 
}


















