package comment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.ConnectionProvider;
import service.Service;

public class CommentRegServiceImpl implements Service {

	CommentDao dao;
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {

		int resultCnt = 0;
		int bidx =  Integer.parseInt(request.getParameter("bidx"));
		String bid = request.getParameter("mid");
		String cmessage= request.getParameter("cmessage");
		System.out.println("cmessage"+cmessage);
		
		Connection conn = null;
		
		try {
			Comment comment = new Comment();
			comment.setBidx(bidx);
			comment.setBid(bid);
			comment.setCmessage(cmessage);
			
			conn = ConnectionProvider.getConnection();
			
			dao = dao.getInstance();
			
			
			resultCnt = dao.insertComment(conn, comment);
			
			request.setAttribute("resultCnt", resultCnt);	
			System.out.println("댓글 입력 성공 1=="+resultCnt);
			
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
		
		return "/WEB-INF/views/comment/commentReg.jsp";
	}

}
