package com.bit.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.type.TrueFalseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User클래스가 밑의 변수를 읽어서, mysql에 테이블을 생성하게 한다.
// @DynamicInsert   insert시 null인 필드가 있는 컬럼을 제외해서, 입력해줌. 25강.
//위, 이렇게 jpa가 제공하는 것에 의존하면, 발전이 없다.
// 18강

//------------------------User테이블 만들기 하는 것--------------------------------
public class User { 		// User테이블 만들기 하는 것. id, username, password, email, role, createDate
	
	@Id // int id가 PK인 것을 알려주기 위해.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 1000, unique = true)// unique = true. username이 중복 안되게.
	private String username;

	@Column(nullable = false, length = 100) //나중에 해쉬로 암호화 할거라서, 길게 잡음.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	
	// @ColumnDefault("'user'") 25강에서 생략함.
	//private String role; //Eum을 쓸것.  관리자/일반유저/매니저
	
	
	//DB에는 RoleType이 없으므로, db에게 해당 enum이 string이라고 알려줘야.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Eum(<-도메인 만들어 주는 것)을 쓸것.  ADMIN/USER
// private String role; 이렇게 해버리면, 가입하는 사람이 롤을 군인처럼 써버릴 수 있다.  	
	
	private String oauth; //kakao와 일반 로그인 한 사람 구분 위해. 65강. 
	
	@CreationTimestamp //시간이 자동으로 입력되게.
	private Timestamp createDate;
	
}
