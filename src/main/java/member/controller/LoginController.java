package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/member/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		Member member = new Member(memberId, memberPw);
		
		// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
		// 한 명의 정보만 불러오기때문에 member 객체 사용!
		MemberService service = new MemberService();
//		Member mOne = service.selectCheckLogin(memberId, memberPw); => 이렇게도 가능!
		Member mOne = service.selectCheckLogin(member);
		
		// 로그인 세션 저장!
		if(mOne != null) {
			// 로그인 성공 시 세션을 가져와야 하기 때문에, if문 안으로 들어와야 함.
			HttpSession session = request.getSession();
			session.setAttribute("memberId", mOne.getMemberId());
			session.setAttribute("memberName", mOne.getMemberName());
			// 로그인 성공!
			request.setAttribute("msg", "로그인 성공!");
			request.setAttribute("url", "/index.jsp");	// 탈퇴 시 index 웹화면으로 이동
			RequestDispatcher view =  request.getRequestDispatcher("/member/serviceSuccess.jsp");
			view.forward(request, response);
		} else {
			// 로그인 실패!
			request.setAttribute("msg", "로그인이 되지 않았습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/member/serviceFailed.jsp");
			view.forward(request, response);
		}
	}
}
