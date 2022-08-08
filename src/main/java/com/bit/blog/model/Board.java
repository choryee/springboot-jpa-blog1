package com.bit.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
//------------------------Board테이블 만들기 하는 것--------------------------------
public class Board { // id, title, content, count, createDate, (user, reply는 FK설정과 연결됨)
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
	
	
	@Column(nullable = false, length = 100)
	private String title;
	
	
	@Lob //대용량 데이터 사용시 사용.
	private String content; //썸머노트쓸건데, <html>태그가 섞여서 디자인되므로 용량이 커진다.
	
	
	//@ColumnDefault("0")
	private int count;
	
	// 22강.
	@ManyToOne(fetch = FetchType.EAGER) //Board테이블 선택할때, 무조건 user라는 FK를 가져오라는 것.
	//@ManyToOne에서 Many는 Board, One는 User이다.
	@JoinColumn(name="userId")//테이블만들어 질때, user는 FK가 되어, userId로 컬럼명이 만들어지다.20강. 
	private User user; //DB는 오브젝트를 저장할수없다. 그래서.FK를 사용함. 하지만, JPA ORM에서는 오브젝트로 저장이 가능.20강.
	
	
	// 22강.
	//@OneToMany(mappedBy = "board") //mappedBy: reply는 연관관계의 주인이 아니다 의미. 
	// "board"는 reply.java의 board임. 여기 밑 reply는  reply.java의 board에 매핑되어 있다.
	//즉, reply를 board테이블에 FK로 만들지 마세요 의미.
	// 위 만든 이유. select할 때 쓸려고. 53강.
	// 밑 fetch = FetchType.LAZY) 는 @OneToMany경우에는, 자동생성이므로 생략가능. 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	private List<Reply> reply; //reply는 여러건이므로 List로 들고와야.
	
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
}
