<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>



<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label>
			 <input type="text" name="username" class="form-control" placeholder="Enter name" id="username">
		</div>
		
			
		<div class="form-group">
			<label for="password">Password:</label>
			 <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
			
		<button id="btn-login" class="btn btn-primary">로그인 </button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=c27675d850e4288d6d95c607a39ac4c5&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"/></a>
		
	</form>


</div>

<%--<script src="/js/user.js"></script> 49강 시큐리티하면서 주석함.  --%>
<%@ include file="../layout/footer.jsp"%>  
