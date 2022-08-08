package com.bit.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bit.blog.model.User;

//24강.

// JpaRepository<User, Integer>에서 JpaRepository는 user테이블을 관리는 Repository임. DAO와 비슷.
// JpaRepository상속하면 자동으로 bean을 등록되어, @Repository 생략가능

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//select * from user where username=?; 52강
	Optional<User> findByUsername(String username); //findByUsername에서 Username는 대문자로 시작해야.
	

}


//public interface UserRepository extends JpaRepository<User, Integer>{
	
	//JPA naming 쿼리. 46강
	//select * from user where username=? AND password=?;
	//User findByUsernameAndPassword(String username, String password); 49강에서 시큐리티하면서 주석함.
	
	
	//네이티브 쿼리라 부름. 위와 동일.
//	@Query(value = "select * from user where username=? AND password=?;", nativeQuery = true)
//	User login(String username, String password);

//}



















