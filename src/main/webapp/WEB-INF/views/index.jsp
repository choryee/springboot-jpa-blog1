<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
  
  <%@ include file="layout/header.jsp"%>
</head>


<%--@page import="java.util.Vector"%>

<jsp:useBean id="pMgr" class="model.Board"/>

<%
	Vector<ProductBean> pvlist=pMgr.getProductList();
--%>


<div class="container" th:inline>
	
	<div th:each="board : ${boards.content}" class="card m-2" >
		<div   class="card-body">
		<h4 class="card-title"><span th:text="${board.title}"></span></h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	

<%--  </tr> --%>

</div>


<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.1</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>

<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.2</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
	<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것3.</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
	<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.4</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>

<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것5.</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
	<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.6</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
	<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.7</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>

<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.8</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
	<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.9</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>
	
</div>
</div>
<%@ include file="layout/footer.jsp"%>
</html>
