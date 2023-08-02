package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class ModifyController
 */
@WebServlet("/notice/modify.do")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// a태그는 get 방식으로 동작함 -> 페이지 이동
		// SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ?
		NoticeService service = new NoticeService();
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice notice = service.selectOneByNo(noticeNo);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/WEB-INF/views/notice/modify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// modify.jsp 에서 작성 버튼 누르면 동작
		request.setCharacterEncoding("UTF-8");
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice notice = new Notice(noticeNo, noticeSubject, noticeContent);
		// UPDATE NOTICE_TBL SET NOTICE_SUBJECT =?, NOTICE_CONTENT = ? WHERE NOTICE_NO = ?;
		NoticeService service = new NoticeService();
		int result = service.updateNotice(notice);
		if(result > 0) {
			// 성공하면 상세페이지로 이동(리스트로 이동)
//			request.getRequestDispatcher("/notice/list.do").forward(request, response);
			response.sendRedirect("/notice/list.do");
		} else {
			// 실패하면 실패 메세지 출력, 공지사항 수정이 완료되지 않았습니다.
			request.setAttribute("msg", "공지사항 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFailed.jsp")
			.forward(request, response);
		}
	}

}
