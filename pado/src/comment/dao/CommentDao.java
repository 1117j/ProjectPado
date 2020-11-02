package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comment.model.Comment;

public class CommentDao {
	
	private CommentDao() {}
	
	static private CommentDao dao = new CommentDao();
	
	public static CommentDao getInstance() {
		return dao;
	}

	public int insertComment(Connection conn, Comment comment) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "Insert into project.comment(bidx, cmessage, bid) values (?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBidx());
			pstmt.setString(2, comment.getCmessage());
			pstmt.setString(3, comment.getBid());
			
			result = pstmt.executeUpdate();
			System.out.println("데이터베이스 입력완료");
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		
		return result;
	}
}
