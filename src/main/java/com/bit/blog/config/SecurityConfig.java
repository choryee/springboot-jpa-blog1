package com.bit.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bit.blog.config.auth.PrincipalDetailService;

//49강

@Configuration //빈 동록해주는 것. 그 의미는 스프링컨테이너에서 객체를 관리할 수 있게 하는 것 의미.
@EnableWebSecurity //시큐리티라는 필터를 추가하는 것.
//위는 스프링 시큐리티가 이미 활성화되어 있지만, 어떤 설정을 밑에서 하겠다는 의미.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근하면 권한 및 인증을 미리 체크하겠다 의미
//위 세 어노테이션는 세트라 항상 같이 써라.

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IoC가 되어 스프링이 관리하게 됨.
	public BCryptPasswordEncoder encodePWD() { //50강
		return new BCryptPasswordEncoder();
	}
	
	 @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	    	return super.authenticationManagerBean();
	    }
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데, 해당 password가 뭘로 해쉬가 되어 회원가입이
	//되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다. 52강
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf토큰을 비활성화(테스트시 걸어 주는 게 좋다)
			.authorizeRequests()
				.antMatchers("/","/auth/**", "/js/**", "/css/**","/image/**","/dummy/**") 
				// 위 /auth/**로 시작하는 것은 모두 인증된 사용자에게만 허용됨. 위 페이지가 아닌 모든 요청은 밑 ("/auth/loginForm")으로 가게된다.
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") //스피링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서, 대신 로그인 해줌.
				.defaultSuccessUrl("/"); // 정상적으로 요청이 완료되면, 왼쪽주소로 이동시켜줌. 52강.
	}
	
	
}
