package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import jdbc.ConnectionProvider;
import service.Service;

public class BoardDeleteServiceImpl implements Service {
	
	BoardDao dao;

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		String msg = "";

		Connection conn = null;

		try {

			int idx = Integer.parseInt(request.getParameter("idx"));

			conn = ConnectionProvider.getConnection();
			dao = BoardDao.getInstance();

			int resultCnt = dao.boardDelete(conn, idx);
			System.out.println("숫자나옴?"+resultCnt);
			switch (resultCnt) {
			case 0:
				msg = "요청하신 게시물이 존재하지 않습니다.";
			case 1:
				msg = "정상적으로 삭제되었습니다.";
			}

		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		} catch (NumberFormatException ex) {
			System.out.println("숫자 형식이 아닌 데이터 요청입니다.");
			msg = "잘못된 요청입니다. 정상적인 경로를 이용해주세요.";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		request.setAttribute("msg", msg);
		
		
		
		return "/WEB-INF/views/board/boardDelete.jsp";
	}

}
