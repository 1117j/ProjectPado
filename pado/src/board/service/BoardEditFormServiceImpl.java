package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import service.Service;

public class BoardEditFormServiceImpl implements Service {
	
	BoardDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		Connection conn;
		Board board = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = BoardDao.getInstance();
			
			int idx = Integer.parseInt(request.getParameter("bidx"));
			board = dao.selectByIdx(conn, idx);
			
			
		} catch (NumberFormatException e) {
			System.out.println("숫자 변경이 불가능한 문자열로 입력!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/boardEditForm.jsp";
	}

}
