package comment.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.dao.CommentDao;
import comment.model.Comment;
import service.Service;

public class CommentListService implements Service {

	CommentDao dao;
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		
		List<Comment> cList = new ArrayList<Comment>();
		
		return "/WEB-INF/views/comment/commentList.jsp";
	}

}
