package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import service.Service;

public class BoardListServiceImpl implements Service {

	BoardDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			dao = BoardDao.getInstance();

			List<Board> boardList = null;

			boardList = dao.selectList(conn);

			System.out.println(boardList);

			request.setAttribute("boardList", boardList);
			//request.setAttribute("commnet", comment);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}


		return "/WEB-INF/views/board/boardList.jsp";

	}
}
