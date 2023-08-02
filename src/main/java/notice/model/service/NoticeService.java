package notice.model.service;

import java.sql.*;
import java.util.List;

import member.common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	private NoticeDAO nDao;
	private JDBCTemplate jdbcTemplate;
	
	public NoticeService() {
		nDao = new NoticeDAO();
		jdbcTemplate = JDBCTemplate.getInstance();	// 싱글톤 패턴 사용
		
	}
	// 공지 제목/내용 데이터 추가
	public int insertNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.insertNotice(conn, notice);
		// 커밋, 롤백
		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);	// jdbcTemplate 연결 닫아주기
		return result;
	}
	// 공지사항 전체 조회하기
	public PageData selectNoticeList(int currentPage) {
		Connection conn = jdbcTemplate.createConnection();
		List<Notice> nList = nDao.selectNoticeList(conn, currentPage);
		String pageNavi = nDao.generatePageNavi(currentPage);
		// List, String 두 데이터 값 전부 보내는 방법
		// 1. Map 이용
		// 2. VO클래스 이용
		PageData pd = new PageData(nList, pageNavi);
		jdbcTemplate.close(conn);
		return pd;
	}
	// 상세 조회
	public Notice selectOneByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		jdbcTemplate.close(conn);
		return notice;
	}
	// 데이터 삭제
	public int deleteNoticeByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.deleteNoticeByNo(conn, noticeNo);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);	// jdbcTemplate 연결 닫아주기
		return result;
	}
	// 데이터 수정
	public int updateNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.updateNotice(conn, notice);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

}
