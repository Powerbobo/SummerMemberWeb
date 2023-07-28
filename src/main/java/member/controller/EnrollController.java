package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollController
 */
@WebServlet("/member/register.do")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		String memberName = request.getParameter("member-name");
		int memberAge = Integer.parseInt(request.getParameter("member-age"));
		String memberGender = request.getParameter("member-gender");
		String memberEmail = request.getParameter("member-email");
		String memberAddress = request.getParameter("member-address");
		String memberPhone = request.getParameter("member-phone");
		String memberHobby = request.getParameter("member-hobby"); // 5ta1
		
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		// 서비스 호출
		MemberService service = new MemberService();
		// INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)
		// 전달값이 있기 때문에 멤버변수가 있어야 함!
		// INSERT 는 1 행을 삽입하기 때문에 int 데이터 타입 사용
		int result = service.insertMember(member);
		if(result > 0) {
			// 성공하면 성공 페이지로 이동 -> RequestDispatcher
			request.setAttribute("msg", "회원가입 성공했어요");
			request.setAttribute("url", "/index.jsp");	// 탈퇴 시 index 웹화면으로 이동
			request.getRequestDispatcher("/member/serviceSuccess.jsp")
			.forward(request, response);
		} else {
			// 실패하면 실패 페이지로 이동 -> RequestDispatcher
			request.getRequestDispatcher("/member/serviceFailed.jsp")
			.forward(request, response);
		}
	}
}
