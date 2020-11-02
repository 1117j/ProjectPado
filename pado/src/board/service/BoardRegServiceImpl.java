package board.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import service.Service;

public class BoardRegServiceImpl implements Service {
	BoardDao dao;
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {

		// 파일 업로드 - 사진
		// 사용자 데이터를 받기 - uid,upw,uname,uphoto
		int resultCnt = 0;
		// 데이터 베이스에 입력할 데이터 변수
		String bid = null;
		String bphoto = null;
		String bmessage = null;
		String baddr = null;
		Connection conn = null;

		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			if (isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				List<FileItem> items = upload.parseRequest(request);

				Iterator<FileItem> ite = items.iterator();

				while (ite.hasNext()) {

					FileItem item = ite.next();

					// isFormField() : text value를 가지는 input 확인
					if (item.isFormField()) { // type=file 이외의 input
						// 파라미터 이름
						String paramName = item.getFieldName();
						// 파라미터의 값
						String paramValue = item.getString("utf-8");
						System.out.println(paramName + "=" + paramValue);

						if (paramName.equals("mid")) {
							bid = paramValue;
						} else if (paramName.equals("bmessage")) {
							bmessage = paramValue;
						} else if (paramName.equals("baddr")) {
							baddr = paramValue;
						} else if (paramName.equals("bphoto")) {
							bphoto = paramValue;
						}
					} else { // type=file

						// 서버 내부의 경로
						String uri = "/upload/post";

						// String uri =
						// request.getSession().getServletContext().getInitParameter("uploadPath");
						// 시스템의 실제 경로
						String realPath = request.getSession().getServletContext().getRealPath(uri);
						System.out.println(realPath);
						String newFileName = System.nanoTime() + "_" + item.getName();
						System.out.println("newFileName : " + newFileName);
						// 서버의 저장소에 실제 저장
						File saveFile = new File(realPath, newFileName);
						System.out.println("saveFile : " + saveFile);
						item.write(saveFile);
						System.out.println("저장완료!!!!!!!!!!!!!!!!!");

						bphoto = uri + "/" + newFileName;
					}
				}

				// 데이터베이스 저장
				Board board = new Board();
				board.setBid(bid);
				board.setBmessage(bmessage);
				board.setBaddr(baddr);
				board.setBphoto(bphoto);

				conn = ConnectionProvider.getConnection();

				dao = BoardDao.getInstance();

				resultCnt = dao.insertBoard(conn, board);
				System.out.println(resultCnt);
				System.out.println(board);

				request.setAttribute("board", board);
				request.setAttribute("result", resultCnt);

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
		return "/WEB-INF/views/board/boardReg.jsp";
	}

}
