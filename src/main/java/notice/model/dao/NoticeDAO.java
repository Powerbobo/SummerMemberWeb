package notice.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import notice.model.vo.Notice;

public class NoticeDAO {
	// 공지 제목/내용 데이터 추가
	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE_TBL VALUES(SEQ_NOTICENO.NEXTVAL,?,?,'admin',DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			result = pstmt.executeUpdate();	// 쿼리문 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	// 데이터 수정
	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE_TBL SET NOTICE_SUBJECT = ?, NOTICE_CONTENT = ? WHERE NOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	// 데이터 삭제
	public int deleteNoticeByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();	// 쿼리문 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 페이징 메소드
	public String generatePageNavi(int currentPage) {
		// 전체 게시물의 갯수 : 37
		// 1페이지 보여줄 게시물 수 : 10
		// 범위의 갯수(네비게이터의 수) : ? -> 4
		
		// 전체 게시물의 갯수 : 55
		// 1페이지 보여줄 게시물 수 : 10
		// 범위의 갯수(네비게이터의 수) : ? -> 6
		
		// 전체 게시물의 갯수 : 76
		// 1페이지 보여줄 게시물 수 : 10
		// 범위의 갯수(네비게이터의 수) : ? -> 8
		int totalCount = 209;	// 전체 게시물의 갯수를 동적으로 가지고 와야함!
		int recordCountPerPage = 10;
		int naviTotalCount = 0;
		// 전체 게시물수 % 한페이지에 보여줄 게시글 수가 0보다 크면
		if(totalCount % recordCountPerPage > 0) {
			// 페이지 개수 = 전체 게시물수 / 한페이지에 보여줄 게시글 수 + 1 (소수점 있을 경우)
			naviTotalCount = totalCount / recordCountPerPage + 1;
		} else {
			// 페이지 개수 = 전체 게시물수 / 한페이지에 보여줄 게시글 수
			naviTotalCount = totalCount / recordCountPerPage;
		}
		int naviCountPerPage = 10;	// 한 페이지 범위에 보여질 페이지의 개수
		// currentPage		startNavi		endNavi
		//  1,2,3,4,5			1			   5
		// 6,7,8,9,10			6		  	  10
		// 11,12,13,14,15		11			  15
		// 16,17,18,19,20	    16			  20
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		// endNavi값이 총 범위의 갯수보다 커지는 것을 막아주는 식
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		// 이전/다음 페이지 여부
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == naviTotalCount) {
			
		}
//		Stirng result = "";
		StringBuilder result = new StringBuilder();
		if(needPrev) {
			result.append("<a href='/notice/list.do?currentPage="+(startNavi-1)+"'>[이전]</a> ");
		}
		for(int i = startNavi; i<= endNavi; i++) {
//			result += "<a href=\"#\">1</a>";
			result.append("<a href='/notice/list.do?currentPage="+i+"'>"+i+"</a>&nbsp;&nbsp;");
		}
		if(needNext) {
			result.append("<a href='/notice/list.do?currentPage="+(endNavi+1)+"'>[다음]</a> ");
		}
		return result.toString();
	}
	
	// 데이터(공지사항) 전체 목록 조회
	public List<Notice> selectNoticeList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> nList = null;
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) ROW_NUM, NOTICE_TBL.* FROM NOTICE_TBL)WHERE ROW_NUM BETWEEN ? AND ?";
		int recordCountPerPage = 10;
		// currentPage   start
		//      1          1
		//      2         11
		//      3         21
		//		4		  16
		// 한 페이지에 게시물 10개씩 보이게끔
		int start = currentPage*recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			// 후처리
			while(rset.next()) {
				Notice notice = rsetToNotice(rset);
				nList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nList;
	}
	// 데이터 상세조회
	public Notice selectOneByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			// 후처리
			if(rset.next()) {
				notice = rsetToNotice(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return notice;
	}
	// 후처리
	private Notice rsetToNotice(ResultSet rset) throws SQLException {
		Notice notice = new Notice();
		notice.setNoticeNo(rset.getInt("NOTICE_NO"));
		notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
		notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
		notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
		notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
		notice.setUpdateDate(rset.getTimestamp("UPDATE_DATE"));
		notice.setViewCount(rset.getInt("VIEW_COUNT"));
		return notice;
	}



}
