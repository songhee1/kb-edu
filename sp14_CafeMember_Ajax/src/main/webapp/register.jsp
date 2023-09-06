<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">  
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <!-- jquery cdn ::  cloudflare, google, ms, jquery -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
form{
	width: 300px;
	margin: 0 auto;
}
</style>

<script>

/* 
key 이벤트 함수
1. keypress()
2. keydown()
3. keyup() ->보통 많이 사용
*/

$(function() {
	$('#id').keyup(function() {
		var id = $(this).val();
		//id값이 4자 이상 8자 이하 일때만 id값을 받아들이겠다
		alert(id);
		
		if(id.length >= 4 & id.length <= 8){
			//비동기 요청
			$.ajax({
				type : 'post',           // 타입 (get, post, put 등등)
			    url : 'idExist.do',      // 요청할 서버url
			    data : 'id = '+ id,      //데이터 보내는 방법 (post요청으로)
			   
			   success : function(result) { // 결과 성공 콜백함수
				   //이미 사용중인 아이디라고 id가 check 영역에 작성
				   //사용 가능 아이디면 id가 check 영역에 출력
				   //alert(result);
			   
				   var jsonData = JSON.parse(result);
				   if(jsonData.check == true){
					   $('#check').html('이미 사용 중인 아이디 입니다.')css('color', 'red');
						$('#submit_btn').attr('disabled', true); //비활성화
				   }else{
					   $('#check').html('사용 가능한 아이디 입니다.')css('color', 'blue');
						$('#submit_btn').attr('disabled', false); //비활성화
				   }
			    },
			    error : function(request, status, error) { // 결과 에러 콜백함수
			        console.log(error)
			    }
			    
			});//ajax
		}else{
			$('#check').html('4자 이상, 8자 이하만 가능!').css('color', 'orange');
			$('#submit_btn').attr('disabled', true); //비활성화
		}
	
	})
})

</script>


</head>
<body>
<div class="container">
	<div class="jumbotron" align="center">
		<h2>REGISTER MEMBER FORM</h2>
	</div>
</div>
<hr>
<div class="container">	
	 <form action="register.do" method="post">	
    <div class="form-group">
      <label for="id">ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter Id" name="id">   
      <span id = "check" style = "margin-left:15px"></span>  
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>
        <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
    </div>
        <div class="form-group">
      <label for="address">Address:</label>
      <input type="text" class="form-control" id="address" placeholder="Enter Address" name="address">
    </div>
 
    <button type="submit" class="btn btn-primary" id="submit_btn">Submit</button>
  </form>

</div>

</body>
</html>