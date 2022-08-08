package com.bit.blog.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.blog.model.Board;
import com.bit.blog.model.RoleType;
import com.bit.blog.model.User;
import com.bit.blog.repository.BoardRepository;
import com.bit.blog.repository.UserRepository;



//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. 즉, IoC해준다는 의미. 메모리에 대신 띄운준다 의미. 39강.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	
	
	@Transactional
	public void 글쓰기(Board board, User user) { //title, content
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
	}
	
//	public List<Board> 글목록(){
//		return boardRepository.findAll();
//	}
	
	//55강. 페이징 위해 변경
	public Page<Board> 글목록(org.springframework.data.domain.Pageable pageable){
		return boardRepository.findAll(pageable); //pageable매개변수 쓰면, 리턴 타입이 List가 아니라, Page타입으로 됨.
	}
	
	
	//밑은 49강 시큐리티하면서, 주석해버림.
//	@Transactional(readOnly = true) //select할때 트렌젝션이 시작되고, 서비스가 종료시 트렌젝션이 종료되는데 그 동안 정합성이 유지됨.
//	public User 로그인(User user) { //46강.
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
}










