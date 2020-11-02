package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class LogoutServiceImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "/WEB-INF/views/member/logout.jsp";
	}

}
