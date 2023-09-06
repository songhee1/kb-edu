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
		//버튼클릭하면 함수 호출
		$('#asynchro').click(function() {
			//비동기 시작 - 데이터를 가지고 온다,
			$.ajax({
				//요청
				type : 'get',
				url : 'asynch',
				
				//응답
				success : function(data) { //객체 단위로 데이터를 받아온다(json타입)
					//alert(data);
					var jsonData = JSON.parse(data);
					//alert(jsonData);
					$('#jsonView').html("<h3><font color=tomato>"+jsonData.person.name+
							"님이 사는 곳은 "+ jsonData.person.address + "!!</font></h3>");
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
<input type = "button" value = "비동기통신 시작하기" id = "asynchro">
<span id = "jsonView"></span>
</body>
</html>