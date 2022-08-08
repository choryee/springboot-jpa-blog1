package com.bit.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bit.blog.model.Board;
import com.bit.blog.model.User;

// JpaRepository<User, Integer>에서 JpaRepository는 user테이블을 관리는 Repository임. DAO와 비슷.
// 자동으로 bean을 등록한다.
// @Repository 생략가능

public interface BoardRepository extends JpaRepository<Board, Integer>{
	

	

}


//public interface UserRepository extends JpaRepository<User, Integer>{
	
	//JPA naming 쿼리. 46강
	//select * from user where username=? AND password=?;
	//User findByUsernameAndPassword(String username, String password); 49강에서 시큐리티하면서 주석함.
	
	
	//네이티브 쿼리라 부름. 위와 동일.
//	@Query(value = "select * from user where username=? AND password=?;", nativeQuery = true)
//	User login(String username, String password);

//}



















