package com.bit.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bit.blog.model.User;
import com.bit.blog.repository.UserRepository;

@Service //52강
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때, username, password변수 2개를	가로채는데, password부분 처리는 알아서 함.
	// 밑에서 우리가 password가 DB에 있는지만 확인주면 됨.
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User principal = userRepository.findByUsername(username)
	                .orElseThrow(()->{
	                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
	                }); // Repository에서 반환이 Optional 이라서 orElseThrow 사용
	        return new PrincipalDetail(principal);//시큐리티 세션에 유저 정보가 저장된다.
	    }
	
	
	
}
