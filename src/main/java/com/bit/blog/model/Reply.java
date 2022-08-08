package com.bit.blog.model;


import java.sql.Timestamp;


import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//------------------------Reply테이블 만들기 하는 것--------------------------------
public class Reply { // id, content, (Board, User는 FK),Timestamp
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
	
	
	@Column(nullable = false, length = 200)
	private String content;
	
	
	@JoinColumn(name="boardId")
	@ManyToOne
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@CreationTimestamp
	private Timestamp createDate;
	

}













