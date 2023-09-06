
<%@page import="com.service.spring.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
<h4>${msg} </h4>
<h2>  회원 조회 결과 </h2>
<%-- ID : <%= vo.getId() %><br>
NAME: <%= vo.getName() %><br>
ADDRESS: <%= vo.getAddress() %><br> --%>
ID : ${vo.id}<br>
NAME:  ${vo.name}<br>
ADDRESS:  ${vo.address}
</body>
</html>












