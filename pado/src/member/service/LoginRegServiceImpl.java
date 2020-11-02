package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.LoginInfo;
import member.model.Member;
import service.Service;
import util.CookieBox;

public class LoginRegServiceImpl implements Service {
	MemberDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// 사용자 요청 정보를 받는다.
				String id = request.getParameter("mid");
				String pw = request.getParameter("mpw");
				String chk = request.getParameter("remember"); 
				System.out.println(id);
				System.out.println(pw);
				String rediectUri =null; 
				int loginCnt = 0;
				
				Connection conn = null;
				
				// 로그인 처리
				Member member = null;
				
				try {
					conn = ConnectionProvider.getConnection();
					dao = MemberDao.getInstance();
					loginCnt = dao.userCheck(id, pw, conn);
					System.out.println(loginCnt);
					member = dao.selectByIdpw(conn, id, pw);
					
					if(member != null){
						
						LoginInfo loginInfo = new LoginInfo(member.getMid(), member.getMname(), member.getMphoto());
						
						request.getSession().setAttribute("loginInfo", loginInfo.getId());

						// 쿠키 설정에 사용한 변수
						String cookieName = "mid";
						String cookiepath = request.getContextPath();
						
						// 회원 아이디 쿠키 설정 
						if(chk!=null){
							response.addCookie(CookieBox.createCookie(cookieName, id, cookiepath, 60*60*24*365));
						} else {
							response.addCookie(CookieBox.createCookie(cookieName, id, cookiepath, 0));
						}
						
						// 로그인 이 필요했던 이전 페이지
						String result = "<script>"
								+ "alert('로그인되었습니다.');"
								+ "</script>";
						request.setAttribute("result", result);
						request.setAttribute("loginCnt", loginCnt);

						System.out.println("loginCnt"+loginCnt);
					} else {
						String result = "<script>"
								+ "alert('아이디 또는 비밀번호가 틀립니다.');"
								+ "history.go(-1);"
								+ "</script>";
						request.setAttribute("result", result);
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				return "/WEB-INF/views/member/loginReg.jsp";
			}
	}


