<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns:th="http://www.thymeleaf.org">


<%@ include file="layout/header.jsp"%>

<%--@page import="java.util.Vector"%>

<jsp:useBean id="pMgr" class="model.Board"/>

<%
	Vector<ProductBean> pvlist=pMgr.getProductList();
--%>


<div class="container">
	
	<%--<tr th:each="board : ${boards}">  --%>
<div th:each="board : ${boards}">
	<div class="card m-2" >
	
	 
		<div th:text="${board.title}"  class="card-body">
		
			<h4 class="card-title">${board.title}</h4>
		
			<a href="#" class="btn btn-primary">상세 보기</a>
		
	</div>
	

<%--  </tr> --%>

</div>


<div >
	<div class="card m-2" >
		<div class="card-body">
			<h4 class="card-title">이건 그냥 만든것.</h4>
			<a href="#" class="btn btn-primary">상세 보기</a>
		</div>
	</div>

</div>
<%@ include file="layout/footer.jsp"%>
</html>
