package notice.model.service;

import java.sql.*;
import java.util.List;

import member.common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

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
	// 공지사항 조회하기
	public List<Notice> selectNoticeList() {
		Connection conn = jdbcTemplate.createConnection();
		List<Notice> nList = nDao.selectNoticeList(conn);
		jdbcTemplate.close(conn);
		return nList;
	}
	// 상세 조회
	public Notice selectOneByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		jdbcTemplate.close(conn);
		return notice;
	}

}
