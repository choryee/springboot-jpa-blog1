package com.bit.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.blog.dto.ResponseDto;
import com.bit.blog.model.RoleType;
import com.bit.blog.model.User;
import com.bit.blog.repository.UserRepository;
import com.bit.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserApiController { // user.js의 ajax에서 url: "/api.user", 이게 호출되어서 만드는 것.
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	//@PostMapping("api/user")
	@PostMapping("/auth/joinProc") //49강 시큐리티 하면서 바꿈.
	 public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController:save 호출됨");
	
		//return new ResponseDto<Integer>(200,1);
		
		//밑 모두 강의 39,40강 다시 봐야. 여러번 수정됨.
		
	
		userService.회원가입(user);
		
		//실제 db에서 insert를 하고 아래에서 리턴하면 된다.
		//return new ResponseDto<Integer>(HttpStatus.OK,1); //여기 1대신 private UserService 값 대입.
		//return new ResponseDto<Integer>(HttpStatus.OK, result);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}	  
		  
		 
}// 끝



//밑. 48강에서 시큐리티 강의하면서 주석처리 한 것.
//@PostMapping("/blog/api/user/login")
//public ResponseDto<Integer>  login(@RequestBody User user, HttpSession session){
//	System.out.println("UserApiController:login 호출됨");
//	User principal=userService.로그인(user);  //principal접근주체
//	
//	if(principal!=null) {
//		session.setAttribute("principal", principal); // header.jsp에 jstl문법 부분에 연결 되는 것.
//	}
//	
//	return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//}


















