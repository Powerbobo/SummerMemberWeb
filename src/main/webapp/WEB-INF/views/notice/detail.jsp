<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세조회</title>
	</head>
	<body>
		<h1>공지사항 상세</h1>
		<ul>
			<li>
				<label>글번호</label>
				<span>${ requestScope.notice.noticeNo }</span>
			</li>
			<li>
				<label>작성일</label>
				<span>${ requestScope.notice.noticeDate }</span>
			</li>
			<li>
				<label>글쓴이</label>
				<span>${ requestScope.notice.noticeWriter }</span>
			</li>
			<li>
				<label>제목</label>
				<span>${ notice.noticeSubject }</span>
			</li>
			<li>
				<label>내용</label>
				<p>${ notice.noticeContent }</p>
			</li>
		</ul>
		<a href="/notice/list.do">목록으로 이동</a><br>
		<a href="/notice/modify.do?noticeNo=${notice.noticeNo}">수정하기</a><br>
<!-- 		form태그가 아니기 때문에, 쿼리스트링을 직접 입력해주어야 함! -->
<!-- 		DELETE * FROM NOTICE_TBL WHERE NOTICE_NO = ? -->
<!-- 		javascript:void(0) -> 기본 클릭 막아주는 코드 / 이벤트 핸들러 등록 -->
		<a href="javascript:void(0)" onclick="deteleCheck();">삭제하기</a><br>
<!-- 		<button id="" or onclick="">삭제하기</button> -->
		<script>
// 			폼태크 post 아니면 전부 get 방식! 하이퍼링크, 클릭해서 이벤트 발생 등
			const deteleCheck = () => {
				const noticeNo = "${notice.noticeNo}";
				if(confirm("정말 삭제하시겠습니까?")){
					location.href="/notice/delete.do?noticeNo="+noticeNo;
				}
			}
		</script>
	</body>
</html>