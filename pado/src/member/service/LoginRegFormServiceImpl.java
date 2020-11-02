package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import service.Service;

public class LoginRegFormServiceImpl implements Service {
	MemberDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
//	변경 시작
		return "/WEB-INF/views/member/loginRegForm.jsp";
	}
//	변경 완료

}
