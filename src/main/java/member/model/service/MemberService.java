package member.model.service;

import java.sql.Connection;

import member.common.JDBCTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	// 멤버변수
	JDBCTemplate jdbcTemplate;	// 연결 생성 1
	MemberDAO mDao;
	// 생성자
	public MemberService() {
//		jdbcTemplate = new JDBCTemplate();
		jdbcTemplate = JDBCTemplate.getInstance();	// JDBCTemplate 에서 가져오기
		mDao = new MemberDAO();
	}
	
	// 열결 생성
	// DAO 호출
	// 커밋/롤백
	
	public Member selectCheckLogin(Member member) {
		// 열결 생성 2
		Connection conn = jdbcTemplate.createConnection();
		// DAO 호출 2(연결도 넘겨줘야 함)
		Member mOne = mDao.selectCheckLogin(conn, member);
		// 커밋/롤백 -> SELECT 할 때에는 할 필요 없음!
		jdbcTemplate.close(conn);
		return mOne;
	}

	// 아이디로 데이터 가져오기
	public Member selectOneById(String memberId) {
		// 열결 생성
		Connection conn = jdbcTemplate.createConnection();
		// DAO 호출
		Member member = mDao.selectOneById(conn, memberId);
		// 커밋/롤백 -> SELECT 할 때에는 할 필요 없음!
		jdbcTemplate.close(conn);
		return member;
	}

	public int insertMember(Member member) {
		// 열결 생성 2
		Connection conn = jdbcTemplate.createConnection();
		// DAO 호출 2
		int result = mDao.insertMember(conn, member);
		// 커밋/롤백
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	// 수정하기
	public int updateMember(Member member) {
		// 열결 생성 2
		Connection conn = jdbcTemplate.createConnection();
		// DAO 호출 2
		int result = mDao.updateMember(conn, member);
		// 커밋/롤백
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	// 탈퇴하기
	public int deleteMember(String memberId) {
		// 연결 생성
		Connection conn = jdbcTemplate.createConnection();
		// DAO 호출(연결 넘겨주기)
		int result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		return result;
	}
}
