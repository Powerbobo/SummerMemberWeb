<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>서비스 결과 성공</title>
	</head>
	<body>
		<h1>서비스 결과 성공!</h1>
		<h2>${ requestScope.msg }</h2>
		<script>
			const result = '${ msg }';
			const url = "${ url }"
			alert(result);
			// 로그인에 성공하면 로그인 페이지로 다시 이동
			location.href = url;	// 탈퇴 시 index 웹화면으로 이동
		</script>
	</body>
</html>