<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- 
���� ���ε� ��� ���� ������ ��

1. �ݵ�� ������ form �±� �ȿ� `input type =��file��`�� ������ �Ѵ�.
2. ����� get ������� ��û�� �Ұ��ϴ�. method ����� post�� �����ϴ�.
3. form �±׿� enctype = "multipart/form-data"�߰�
4. pom.xml�� fileupload ������� �߰�
5. ���� ���ε��� ������ �����ϴ� vo ���� ����� ��û�� ó��(RequestMapping)
6. form input name �̸��� vo �ʵ� ��� ��ġ(���� name�� uploadFile �� �ۼ��ؾ��Ѵ�!)

 -->


</head>
<body>
<h2 align = "center">File Upload Form</h2>
<form action = "fileupload.do" method = "post" enctype = "multipart/form-data">
	����ڸ� <input type = "text" name = "userName">
	<input type = "file" name = "uploadFile">
	<input type = "submit" value = "���� ���ε�">
</form>
</body>
</html>