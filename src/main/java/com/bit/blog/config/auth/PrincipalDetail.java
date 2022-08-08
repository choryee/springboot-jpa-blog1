package com.bit.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bit.blog.model.User;


import lombok.Getter;

// 52강. 
// 스프링 시큐리티가 로인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails{
	
	private User user;

	public PrincipalDetail(User user) { //생성자
		this.user=user;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	
	// 계정이 만료됐는지 여부를 리턴한다(true 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠금됐는지 여부를 리턴한다(true 잠금 안됨)
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	// 비번이 만료됐는지 여부를 리턴한다(true 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	// 계정이 활성화됐는지 여부를 리턴한다(true 활성화)
	@Override
	public boolean isEnabled() {
	
		return true;
	} 
	
	//권한의 여부를 리턴한다.계정이 갖고 있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 돌어야 하는데 우리는 한개만 있음)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors=new ArrayList<>();
		
//		collectors.add(new GrantedAuthority() { //자바는 new GrantedAuthority()부분에 밑의 오버라이드 부분을
//			//못 넣는다.(JS처럼) 그래서, 람다식가 나온것.
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole(); //"ROLE_"스프링에서 롤을 받을 때 규칙이라 넣어줘야.
//			}
//		});
		
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
	
	

}
