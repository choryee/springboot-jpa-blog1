package com.bit.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.blog.model.RoleType;
import com.bit.blog.model.User;
import com.bit.blog.repository.UserRepository;



//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. 즉, IoC해준다는 의미. 메모리에 대신 띄운준다 의미. 39강.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		
		User user=userRepository.findByUsername(username).orElseGet(()->{ //orElseGet(() 없으면 빈 객체 리턴하라 의미
			return new User();
		});
			return user;		
	}
	
	
	@Transactional
	public void 회원가입(User user) { //40강에서 수정함.
		
//		try {
//			userRepository.save(user);
//			return 1; //성공시
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("userRepository:save():"+e.getMessage());
//		}
//		return -1; //실패시
		
		String rawPassword=user.getPassword(); // password 1234의 원문
		String encPassword=encoder.encode(rawPassword); // 스프링 시큐리티로 인해 위 비번 원문이 아주 긴 암호로 해쉬됨.
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	//밑은 49강 시큐리티하면서, 주석해버림.
//	@Transactional(readOnly = true) //select할때 트렌젝션이 시작되고, 서비스가 종료시 트렌젝션이 종료되는데 그 동안 정합성이 유지됨.
//	public User 로그인(User user) { //46강.
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
}










