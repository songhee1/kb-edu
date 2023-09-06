<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- 
파일 업로드 기술 사용시 주의할 점

1. 반드시 무조건 form 태그 안에 `input type =”file”`로 만들어야 한다.
2. 절대로 get 방식으로 요청이 불가하다. method 방식은 post만 가능하다.
3. form 태그에 enctype = "multipart/form-data"추가
4. pom.xml에 fileupload 디펜던시 추가
5. 파일 업로드한 정보를 저장하는 vo 먼저 만들고 요청을 처리(RequestMapping)
6. form input name 이름을 vo 필드 명과 일치(파일 name은 uploadFile 로 작성해야한다!)

 -->


</head>
<body>
<h2 align = "center">File Upload Form</h2>
<form action = "fileupload.do" method = "post" enctype = "multipart/form-data">
	사용자명 <input type = "text" name = "userName">
	<input type = "file" name = "uploadFile">
	<input type = "submit" value = "파일 업로드">
</form>
</body>
</html>