package com.bit.blog.controller;

import java.net.http.HttpHeaders;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
//import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bit.blog.model.KakaoProfile;
import com.bit.blog.model.OAuthToken;
import com.bit.blog.model.User;
import com.bit.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class UserController1 {
	
	@Value("${cos.key}") //application.yml에 설정됨,
    private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	
	
//	@GetMapping("/user/joinForm")
//	public String joinForm() {
//
//		return "user/joinForm";
//}
	
//	@GetMapping("/joinForm") //47강. 시큐리티 이용한 방법.
//	public String joinForm() {
//
//		return "user/joinForm";
//}
//	
	//인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/이하 경로만 허용할 것.
	//그냥 주소가 /이면, index.jsp를 허용
	//static이하에 있는 /js/~, /css/~, /image/~ 허용할 것.
	//바로 위 처럼, 인증이 필요없는 곳에는 auth를 붙일 것.49강.
	@GetMapping("/auth/joinForm") //47강. 시큐리티 이용한 방법.
	public String joinForm() {

		return "user/joinForm";
}
	
	
//	@GetMapping("/user/loginForm")
//	public String loginForm() {
//
//		return "user/loginForm";
//}
	
	@GetMapping("/auth/loginForm") //47강. 시큐리티 이용한 방법.
	public String loginForm() {

		return "user/loginForm";
}
	
	//64강.
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { //public @ResponseBody. Data를 리턴해주는 컨트롤러 함수가 됨
		
		//post방식으로 key=value 테이터를 요청해야(카카오쪽으로)
		RestTemplate rt=new RestTemplate();
		//HttpHeader 오브젝트 생성
		org.springframework.http.HttpHeaders headers=new org.springframework.http.HttpHeaders();
		
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성하는 것
		MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "c27675d850e4288d6d95c607a39ac4c5");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나이 오브젝트에 담기.
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest=
				new HttpEntity<>(params, headers);
		
		//Http요청하기, post방식으로, 그리고 response 변수의 응답을 받음.
		ResponseEntity<String> response=rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
//----------------json테이타를 자바에서 처리할 수 있게 자바 오브젝트로 바꾸는 것. 즉,response로 받은 json->자바 오브젝트로
		//Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper=new ObjectMapper();
		OAuthToken oauthToken=null;
		
		try {
			 oauthToken=objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰:"+oauthToken.getAccess_token());
		
//-------이제 받은 토큰으로, 카카오의 리소스 서버에서 카카오로그인한 회원 정보 가져오기위해 
		//그 회원정보 요청을 위해, 위에서 json->자바오브젝트를 
		// 다시, 자바오브젝트-> json으로 만들어서 카카오 리소스 서버에 보내야-----------
		
		//post방식으로 key=value 테이터를 요청해야(카카오쪽으로)
		RestTemplate rt2=new RestTemplate();
		//HttpHeader 오브젝트 생성
		org.springframework.http.HttpHeaders headers2=new org.springframework.http.HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
			
		//HttpHeader와 HttpBody를 하나이 오브젝트에 담기.
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2=
				new HttpEntity<>(headers2);
		
		//Http요청하기, post방식으로, 그리고 response 변수의 응답을 받음.
		ResponseEntity<String> response2=rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest2,
			String.class
		);
		
//----------------json테이타를 자바에서 처리할 수 있게 자바 오브젝트로 바꾸는 것. 즉, json->자바 오브젝트로		
		ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile=null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		
        //User 오브젝트:username, password, email
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        
        System.out.println("블로그서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("블로그서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        //UUID cosKey=UUID.randomUUID();
        //UUID 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘.
        System.out.println("블로그서버 패스워드 : " + cosKey);
        
        User kakaoUser=User.builder()
        	.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
        	.password(cosKey)
        	.email(kakaoProfile.getKakao_account().getEmail())
        	.oauth("kakao") // User.java에. 카카오로 로그인과 일반 로그인을 DB컬럼에서 구분 표시 해줌.
        	.build();
        
        //이미 가입자인지 미 가입자이니 체크해서 처리
        User originUser=userService.회원찾기(kakaoUser.getUsername());
        if(originUser.getUsername()==null) {
        	System.out.println("기존 회원이 아니므로 자동 회원가입을 진행합니다!!");
        	userService.회원가입(kakaoUser);
        }
        System.out.println("자동 로그인을 진행합니다!!");
        //로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
		
        
		//return response2.getBody();
		return "redirect:/";
	}
	
	
}





















