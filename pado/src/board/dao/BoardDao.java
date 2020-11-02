package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.Board;

public class BoardDao {

	private BoardDao() {
	}

	static private BoardDao dao = new BoardDao();

	public static BoardDao getInstance() {
		return dao;
	}

	public int insertBoard(Connection conn, Board board) throws SQLException {
		int resultCnt = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO project.board(bid,bphoto,bmessage,baddr)VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBid());
			pstmt.setString(2, board.getBphoto());
			pstmt.setString(3, board.getBmessage());
			pstmt.setString(4, board.getBaddr());
			System.out.println("데이터베이스 입력완료");
			resultCnt = pstmt.executeUpdate();
			
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return resultCnt;
	}

	public List<Board> selectList(Connection conn) throws SQLException {
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<Board>();
		
		String sql = "select * from project.board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board(rs.getInt("bidx"),
										rs.getString("bid"),
										rs.getString("bphoto"),
										rs.getString("bmessage"),
										rs.getString("baddr"),
										rs.getDate("bregdate"));
				list.add(board);
			}
			
		} finally {
			
			if(pstmt != null) {
				pstmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
		
		return list;
	}

	public int boardDelete(Connection conn, int idx) throws SQLException {
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		
		String sql = "delete from project.board where bidx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			resultCnt = pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return resultCnt;
	}

	public int boardEdit(Connection conn, Board board) throws SQLException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "update board set "
				   + " bmessage=?, baddr=?, bphoto=? "
				   + " where bidx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBmessage());
			pstmt.setString(2, board.getBaddr());
			pstmt.setString(3, board.getBphoto());
			pstmt.setInt(4, board.getBidx());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("수정됬음");
			}

		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return result;
	}

	public Board selectByIdx(Connection conn, int idx) throws SQLException {
		Board board = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs;	
		
		try {
			String sql = "select * from project.board where bidx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setBidx(rs.getInt("bidx"));
				board.setBid(rs.getString("bid"));
				board.setBphoto(rs.getString("bphoto"));
				board.setBmessage(rs.getString("bmessage"));
				board.setBaddr(rs.getString("baddr"));
				board.setBregdate(rs.getDate("bregdate"));
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return board;
	}




}
