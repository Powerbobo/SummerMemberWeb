<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet"href="/resources/css/member/main.css"> <!--  css 파일 이용 -->
	</head>
	<body>
		<h1>회원가입</h1>
		<h3>회원정보를 입력하세요</h3>
		<form action="/member/register.do" method="post">
			<fieldset>
				<legend>회원가입</legend>
				<ul>
					<li>
						<label for="member-id">아이디</label>
						<input type="text" id="member-id" name="member-id"></input> <!-- 기능을 위한 id, 쿼리스트링 생성을 위한 name -->
					</li>
					<li>
						<label for="member-pw">비밀번호</label>
						<input type="password" id="member-pw" name="member-pw"></input>
					</li>
					<li>
						<label for="member-name">이름</label>
						<input type="text" id="member-name" name="member-name"></input>
					</li>
					<li>
						<label for="member-age">나이</label>
						<input type="text" id="member-age" name="member-age"></input>
					</li>
					<li>
						<label for="member-gender">성별</label>
<!--						기본 성별 수정 코드의 오류를 막기 위해 적어주는 input태그, hidden으로 사용자에겐 보이지 않음! -->
<!-- 						기존 데이터 그대로 업데이트 되도록 함 -->
						<input type="hidden" id="member-gender" name="member-gender" value="${ member.memberGender }">
						<c:if test="${ member.memberGender eq 'M' }">남자</c:if>
						<c:if test="${ member.memberGender eq 'F' }">여자</c:if>
						 남<input type="radio" id="member-gender" name="member-gender" value="M">
						 여<input type="radio" id="member-gender" name="member-gender" value="	F">
					</li>
					<li>
						<label for="member-email">이메일</label>
						<input type="text" id="member-email" name="member-email"></input>
					</li>
					<li>
						<label for="member-address">주소</label>
						<input type="text" id="member-address" name="member-address"></input>
					</li>
					<li>
						<label for="member-phone">전화번호</label>
						<input type="text" id="member-phone" name="member-phone"></input>
					</li>
					<li>
						<label for="member-hobby">취미</label>
						<input type="text" id="member-hobby" name="member-hobby"></input>
					</li>
				</ul> 
				<br>
				<input type="submit" value="가입하기">
				<input type="reset" value="초기화">
			</fieldset>
		</form>
	</body>
</html>