package com.bit.blog.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.blog.model.RoleType;
import com.bit.blog.model.User;
import com.bit.blog.repository.UserRepository;

import jakarta.transaction.Transactional;




//24강. 27강.

@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
// -------------------delete--30강. 첨-----------------------------------------------------	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제실패했습니다. 해당 id는 없습니다";
		}
		
		
		return "삭제되었습니다. id:"+id;
		
	}
	
// -------------------update--28강. 첨----------------------------------------------------	
	
	//'save'함수는 id를 전달하지 않으면, insert를 해주고,
	//'save'함수는 id를 전달하면, 해당 id에 대한 데이터가 있으면, update를 해주고
	//'save'함수는 id를 전달하면, 해당 id에 대한 데이터가 없으면, insert를 해준다.
	
	@Transactional  // //userRepository.save(requestUser); 처럼 save없이도, update됨. 더티 체킹이라고 함.
	// 위 하면, 밑 메소드가 모두 끝날 때, 자동으로 commit되면서, 업데이트 되는 것. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
	//@RequestBody User requestUser 이처럼 json테이터로 요청한 것을 자바 객체로 받아준것. 28강.	
		System.out.println("id:"+id);
		System.out.println("passsword:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user=userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException ("수정 실패했습니다"); 
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(requestUser);
		
		//밑 세줄처럼 해서, save로 업데이트한다면, 미입력항목에 널이 뜨는 단점이 있어, 위 방법 시도.
//		requestUser.setId(id);
//		requestUser.setUsername("ssar");
//		userRepository.save(requestUser);
		
		
		return user;
		
	}
	
// -------------------List 모두 받기-27강.첨.-----------------------------------------------------	
//	public List<User> user1(User user){
//		List<User> u=new ArrayList<>();
//		u.set(4, new(4,"park", "1234","park@naver.com"));
//		
//		return u;
//	}
	
	
	//여러건 데이타 받기
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	
	//한 페이지당 2건에 데이터 받기
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id",
													direction = Sort.Direction.DESC) Pageable pageable){ //55강에서 페이징으로 바꿈.
		
		//List<User> users= userRepository.findAll(pageable).getContent();
		
		Page<User> pagingUser=userRepository.findAll(pageable);
		List<User> users=pagingUser.getContent(); //27강. 끝부분
		return pagingUser;
	}
	
	
	
	// -------------------Join-------------------------------------------------------	
	
	// http://localhost:8000/blog/dummy/join (회원 가입 요청이므로, post방식으로 매핑함)
	// http의 body(x-www-form-urlencorded방식의미, key,value형태)에 username, password, email데이터를 가지고 요청하는 것
	//24강.
	
	@PostMapping("/dummy/join")
	//밑 두개는 동일
// public String join(@RequestParam("username") String username, String password, String email) {}
//	public String join(String username, String password, String email) {
//		System.out.println("username=>"+username);
//		System.out.println("password=>"+password);
//		System.out.println("email=>"+email);
//		 return "회원가입이 완료됨";	
//	}
//	위와 같은 결과인데, 파라미터를 객체를 넣어도 결과 동일.
	public String join(User user) {
		System.out.println("username=>"+user.getUsername());
		System.out.println("password=>"+user.getPassword());
		System.out.println("email=>"+user.getEmail());
		System.out.println("role=>"+user.getRole());
		
		//user.setRole("user"); //이렇게 하면, 유지보수하는 개발자가 입력값을 잘못 입력할수 있으므로, enum으로 만드는것. 25강.
		user.setRole(RoleType.USER); //이렇게 하면, 유지보수하는 개발자가 입력값을 잘못 입력할수 있으므로, enum으로 만드는것.
		
	   userRepository.save(user); 
		 return "회원가입이 완료됨";	
	}
	
	
	// -------------------List 1개 받기--26강.-----------------------------------------------------	
	// 회원 받아오기. 26강.
	// http://localhost:8000/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4 (현재 디비에 3개만 저장된 경우)을 찾으면 내가 디비에서 못 찾게되면, user가 널이 되지 안겠니?
		//그러면 리턴이 널이 된다. 그럼 프로그램운영에 문제가 된다
		//Optional로 너의 User객체를 감싸서 가져올테니, 널인지 아닌지는 네가 판단해서 리턴해. 26강.
		
//		User user=userRepository.findById(id).orElseGet(new Supplier<User>() {
//orElseGet은 인터페이스이므로, 바로 객체생성 안되므로, 익명클래스(new Supplier)만들고, 그 인터가 가진 메소드를 아래처럼 오버라이딩해줘야.		
//			@Override
//			public User get() {
//				return new User(); //4번 넣으면, 빈 객체라도 리턴해줌. 최소 널을 리턴하지 해줌.
//			}
//		});
		
		//위와 다른 방법. 밑은 AOP방식. Aspect Oriented Program.-공통 사항(여기서는 에러)등은 스프링이 가로채서 관리해줌.
//		User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException >() {
//			@Override
//			public IllegalArgumentException  get() {
//				
//				return new IllegalArgumentException ("해당 유저는 없습니다.=> id : "+id); 
//				// 이것도 @Override	public IllegalArgumentException  get()를 오버라이딩 한것.
//			}
//		});
		
		
		//위를 람다식으로.26강.
		User user=userRepository.findById(id).orElseThrow(()->{
			//orElseThrow안에 어떤 타입을 써야 하지는 신경안쓰고(리턴타입을 몰라도된다) 처리하므로, 위 보다 쉬움. 그게 람다식문법.
			return new IllegalArgumentException ("해당 유저는 없습니다.=> id : "); 
			
		});
		//밑처럼 객체로 리턴해줘버리면, 클의 웹브라우저는 그것을 처리 못함. 그래서, 웹브라우저가 이해할 수 있는 데이터로 변환해줘야
		//하는데, 그게 JSON이다.
		//스프링부트 messageConverter가 응답시 자동으로 작동해, 만약에 자바 객체를 리턴하면, 그 컨버터가 작동해
		//자동으로 json으로 변환해 클에게 던져줌.
		return user;
		
	}	
	
}
