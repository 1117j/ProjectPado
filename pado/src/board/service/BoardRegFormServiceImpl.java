package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class BoardRegFormServiceImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "/WEB-INF/views/board/boardRegForm.jsp";
	}

}
