<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
	$(function(){
		//��ưŬ���ϸ� �Լ� ȣ��
		$('#asynchro').click(function() {
			//�񵿱� ���� - �����͸� ������ �´�,
			$.ajax({
				//��û
				type : 'get',
				url : 'asynch',
				
				//����
				success : function(data) { //��ü ������ �����͸� �޾ƿ´�(jsonŸ��)
					//alert(data);
					var jsonData = JSON.parse(data);
					//alert(jsonData);
					$('#jsonView').html("<h3><font color=tomato>"+jsonData.person.name+
							"���� ��� ���� "+ jsonData.person.address + "!!</font></h3>");
				}//callback
			}); //ajax
		});//click
	})
</script>


</head>
<body>
<h3>${info}</h3>
<p></p>
<hr>
<p></p>
<input type = "button" value = "�񵿱���� �����ϱ�" id = "asynchro">
<span id = "jsonView"></span>
</body>
</html>