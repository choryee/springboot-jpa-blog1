package com.bit.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//13강.
// 지금 여기 코드 전체가 '서버'이다.

@RestController //사용자가 요청시, data을 가지고 응답할 때 사용. @Controller는 html파일로 응답(리턴)할 때 사용.
public class HttpControllerTest { //이 Controller는 서버가 클의 요청에 대답하는 것이다.

	 private static final String TAG="HttpControllerTest:";
	 
	 //16강.
	 @GetMapping("/http/lombok")
	 public String lombokTest() {
		 //Member m=new Member(1, "kim","1234","email");
		 //위의 모든 변수 넣은 것과 달리, 변수값을 넣고 싶은 것만 넣을 수 있다. 
		 Member m=Member.builder().username("Lee").password("456").email("go@nate.com").build();
		 System.out.println(TAG+":getter:"+m.getId());
		 m.setId(500);
		 System.out.println(TAG+":setter:"+m.getId());
		 return "lombok test completed=>>"+m.getEmail();
	 }
	
//----------------------------------------------------------------	 
	// http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @RequestParam String username) {
		//@RequestParam int id은 지금 여기 코드 전체가 서버이다. Requestparam은 '요청된 매개변수'라고 해석하라.
		return "get 요청:"+id+":"+username;
	}
	
	// http://localhost:8080/http/post. post는 기존 정보에 추가하라는 것. 
//	@PostMapping("/http/post")
//	public String postTest(Member m) {
//		return "post 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
//	}
	
	
	// http://localhost:8080/http/post. post는 기존 삽입insert에 추가하라는 것. 
//	@PostMapping("/http/post")
//	public String postTest(@RequestBody String text) { //postman에서 body/raw에서 text나 json으로 보낼때는, 
	//@RequestBody해줘야 받을 수 있다.
//		return "post 요청=>"+text;
//	}
	
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //messageConverter가 자동으로 매핑해줌.
		return "post 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	
	// http://localhost:8080/http/put. put는 기존 정보에 업데이트하라는 것. 
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청=>";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청=>";
	}
	
	
}

























