/**
 * 
 */
 
 let index={ //35강.
	init:function(){
		$("#btn-save").on("click", ()=>{ //function()사용하지 않고,  ()=>사용이유는 this를 바인딩하기 위해서.
		//, ()=>{ 부분은 "click"하면 무엇을 할지 의미.
		// 회원가입의 btn-save버튼 누르면, 그 입력을 값을 json으로 전송하는 것. 
			this.save();
		});
		
		/* $("#btn-login").on("click", ()=>{ //function()사용하지 않고,  ()=>사용이유는 this를 바인딩하기 위해서.
			this.login();
		}); 49강 시큐리티 강의하면서 주석함. */
		
	},
	save:function(){
		//alert("user의 save함수가 호출됨");
		let data={
			username:$("#username").val(), //form의 id=username의 값을 찾아, username:에 넣음.
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		// console.log(data); //이 확인은 크롬에서 F12후, 회원가입양식에 값을 넣고, 버튼 누르면, 그 크롬 콘솔창에 자료 보여야함.

	// ajax호출시 디폴트가 비동기식 호출임. 38강
	//ajax 통신을 이용해서 3개의 테이터를 json으로 변경하여 insert요청할 것.
	//회원가입 수행 요청후, 성공이면 done()실행, 실패이면 fail()를 실행하는 것.
	//ajax가 통신에 성공후, 서버가 json를 리턴해주면 자동으로 자바 객체로 변환해준다.
		$.ajax({ //35강
			type:"POST",
			url:"/auth/joinProc", //어느 주소로 갈건데.
			data:JSON.stringify(data), // 이것은 http body데이터임. data라는 것은 JS객체이므로, 자바가 인식못해서, json으로 바꿔줘야.
			contentType:"application/json;charset=utf-8", //38강. body데이터의 타입 형태.
			dataType:"json"  //응답을 json으로 받을 것임 의미. 요청후 서버에서 응답이 왔을 때, 기본적으로 모든 것이 문자열String이다.근데, json으로 오면
			// 밑에 처럼 자바스크립트 객체로 변경해준다.
		}).done(function(resp){
			 alert("회원가입이 완료되었습니다.");
			 //console.log(resp);
			 location.href="/";
			 
		}).fail(function(){
			alert(JSON.stringify(error));
		}); 
	},
	
	
	/*
		login:function(){
		//alert("user의 save함수가 호출됨");
		let data={
			username:$("#username").val(), //form의 id=username의 값을 찾아, username:에 넣음.
			password:$("#password").val(),
			
		};
		
		// console.log(data); //이 확인은 크롬에서 F12후, 회원가입양식에 값을 넣고, 버튼 누르면, 그 크롬 콘솔창에 자료 보여야함.

//joinForm.jsp의 버튼을 클릭하면, 여기 맨위 js부분이 작동하고, 밑 ajax로 데이터를 서버에 보내는 것.
	// ajax호출시 디폴트가 비동기식 호출임. 38강
	//ajax 통신을 이용해서 3개의 테이터를 json으로 변경하여 insert요청할 것.
	//회원가입 수행 요청후, 성공이면 done()실행, 실패이면 fail()를 실행하는 것.
	//ajax가 통신에 성공후, 서버가 json를 리턴해주면 자동으로 자바 객체로 변환해준다.
		$.ajax({ //35강
			type:"POST",
			url:"/api/user/login",
			data:JSON.stringify(data), // 이것은 http body데이터임
			contentType:"application/json;charset=utf-8", //38강. body데이터의 타입 형태.
			dataType:"json"  //응답을 json으로 받을 것임 의미. 요청후 응답외 왔을 때, 기본적으로 모든 것이 문자열String이다.근데, json으로 오면
			// 밑에 처럼 자바스크립트 객체로 변경해준다.
		}).done(function(resp){
			 alert("로그인이 완료되었습니다.");
			 //console.log(resp);
			 location.href="/";
			 
		}).fail(function(){
			alert(JSON.stringify(error));
		});  
	} 
	49강 시큐리티 강의하면서 주석함.*/
	
}

index.init();
 
 
 
 
 
 
 