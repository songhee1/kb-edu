
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
<h2>Login Information</h2>
<%-- ID <%= vo.getId() %><br>
NAME <%=  vo.getName() %><br>
ADDRESS <%= vo.getAddress() %><br> --%>
ID : ${vo.id}
NAME : ${vo.name}
ADDRESS : ${vo.address}
<p></p><hr><p></p>
<a href="logout.do">LOG OUT</a>
<a href="index.jsp">INDEX</a>
</body>
</html>


















