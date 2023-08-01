<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 회원가입</title>
		<link rel="stylesheet"href="/resources/css/student/main.css">
	</head>
	<body>
		<h1>학생 회원가입</h1>
		<h3>학생 정보를 입력하세요!</h3>
			<form action="student/register.do">
				<fieldset>
					<legend>회원가입</legend>
					<ul>
						<li>
							<label for="student-id">아이디</label>
							<input type="text" id="student-id" name="student-id"></input>
						</li>
						<li>
							<label for="student-pw">비밀번호</label>
							<input type="password" id="student-pw" name ="student-pw">
						</li>
						<li>
							<label for="student-name">이름</label>
							<input type="text" id="student-name" name ="student-name">
						</li>
						<li>
							<label for="student-age">나이</label>
							<input type="text" id="student-age" name ="student-age">
						</li>
						<li>
							<label for="student-gender">성별</label>
							<input type="text" id="student-gender" name ="student-gender">
						</li>
						<li>
							<label for="student-email">이메일</label>
							<input type="text" id="student-email" name ="student-email">
						</li>
						<li>
							<label for="student-address">주소</label>
							<input type="text" id="student-address" name ="student-address">
						</li>
						<li>
							<label for="student-phone">전화번호</label>
							<input type="text" id="student-phone" name ="student-phone">
						</li>
						<li>
							<label for="student-hooby">취미</label>
							<input type="text" id="student-hobby" name ="student-hobby">
						</li>
					</ul>
				</fieldset>
			</form>
	</body>
</html>