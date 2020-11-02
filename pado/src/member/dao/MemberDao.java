package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.model.Member;

public class MemberDao {


	private MemberDao() {
	}
	private static MemberDao dao = new MemberDao();
	public static MemberDao getInstance() {
		return dao;
	}

	//회원가입 정보받기
	public int insertMember(Connection conn, Member member) throws SQLException {
		int resultCnt = 0;

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO project.member (mid, mpw, mname, mphonenumber, mphoto ) VALUES (?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMpw());
			pstmt.setString(3, member.getMname());
			pstmt.setString(4, member.getMphonenumber());
			pstmt.setString(5, member.getMphoto());

			resultCnt = pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

		return resultCnt;
	}

	//아이디가 있는지 확인하기
	public int selectById(Connection conn, String id) throws SQLException {
		
		int resultCnt = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs;	
		
		try {
			String sql = "select count(*) from project.member where mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultCnt = rs.getInt(1);
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return resultCnt;
	}
	
	public List<Member> selectTotalList(Connection conn) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Member> memberList = new ArrayList<Member>();
		
		String sql = "select * from project.member order by mname";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMidx(rs.getInt("midx"));
				member.setMid(rs.getString("mid"));
				member.setMpw(rs.getString("mpw"));
				member.setMname(rs.getString("mname"));
				member.setMphonenumber(rs.getString("mphonenumber"));
				member.setMphoto(rs.getString("mphoto"));
				member.setMregdate(rs.getDate("mregdate"));
				
				memberList.add(member);
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return memberList;
	}
	

	//가입한 전체회원수
	public int selectTotalCount(Connection conn) throws SQLException {
		int resultCnt = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select count(*) from project.member");
			
			if(rs.next()) {
				resultCnt = rs.getInt(1);
			}
			
		} finally {
			if(stmt!=null) {
				stmt.close();
			}
		} 
		
		return resultCnt;
	}

	//가입한 회원들을 리스트에 담아 보여주기. limit을 사용하여 한페이지에 보여줄 갯수설정.
	public List<Member> selectList(Connection conn, int startRow, int count) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Member> memberList = new ArrayList<Member>();
		
		String sql = "select * from project.member order by mname limit ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, count);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMidx(rs.getInt("midx"));
				member.setMid(rs.getString("mid"));
				member.setMpw(rs.getString("mpw"));
				member.setMname(rs.getString("mname"));
				member.setMphonenumber(rs.getString("mphonenumber"));
				member.setMphoto(rs.getString("mphoto"));
				member.setMregdate(rs.getDate("mregdate"));
				
				memberList.add(member);
			}
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return memberList;
	}
	
	//가입한 회원을 midx를 사용하여 삭제
	public int memberDelete(Connection conn, int idx) throws SQLException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from project.member where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return result;
		
		
	}
	
	//midx로 회원 뽑기
		public Member selectByIdx(Connection conn, int idx) throws SQLException {

			Member member = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs;	
			
			try {
				String sql = "select * from project.member where midx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new Member();
					member.setMidx(rs.getInt("midx"));
					member.setMid(rs.getString("mid"));
					member.setMpw(rs.getString("mpw"));
					member.setMname(rs.getString("mname"));
					member.setMphonenumber(rs.getString("mphonenumber"));
					member.setMphoto(rs.getString("mphoto"));
					member.setMregdate(rs.getDate("mregdate"));
				}
				
			} finally {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			
			return member;
		}

		//midx에 맞는 회원 수정하기
		public int editMember(Connection conn, Member member) throws SQLException {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = "update project.member set mpw=?, mphonenumber=?, mphoto=? "
					   + " where midx=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMpw());
				pstmt.setString(2, member.getMphonenumber());
				pstmt.setString(3, member.getMphoto());
				pstmt.setInt(4, member.getMidx());
				
				result = pstmt.executeUpdate();
				
			} finally {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			
			return result;
		}
		
		public Member selectByIdpw(Connection conn, String id, String pw) throws SQLException {
			
			PreparedStatement pstmt = null;
			ResultSet rs;	
			Member member = null;
			
			try {
				String sql = "select * from project.member where mid=? and mpw=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new Member();
					member.setMidx(rs.getInt("midx"));
					member.setMid(rs.getString("mid"));
					member.setMpw(rs.getString("mpw"));
					member.setMname(rs.getString("mname"));
					member.setMphonenumber(rs.getString("mphonenumber"));
					member.setMphoto(rs.getString("mphoto"));
				}
				
			} finally {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			
			return member;
		}
		
		
		public int userCheck(String id, String pw, Connection conn) {

		      int result = 0;
		      PreparedStatement pstmt = null;
		      ResultSet rs;

		      try {

		         String sql = "select mpw from project.member where mid=?";

		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, id);

		         rs = pstmt.executeQuery();
		         if (rs.next()) {
		            if (rs.getString("mpw").equals(pw)) {
		               result = 1; // 로그인됨
		            } else {
		               result = 0;
		            }
		         }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {

		         try {
		            if (pstmt != null)
		               pstmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }

		      }
		      return result;
		      
		   }
		
		
		public Member userInfo(String id, Connection conn) {
		      Member member=null;
		      PreparedStatement pstmt = null;
		      ResultSet rs;
		      
		      try {
		               
		         String sql = "select * from project.member where mid=?";

		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, id);
		         
		         rs = pstmt.executeQuery();
		         if(rs.next()) {
		            member = new Member();
		            member.setMidx(rs.getInt("midx"));
		            member.setMid(rs.getString("mid"));
		            member.setMpw(rs.getString("mpw"));
		            member.setMname(rs.getString("mname"));
		            member.setMphoto(rs.getString("mphoto"));
		            member.setMphonenumber(rs.getString("mphonenumber"));
		         }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         
		            try {
		               if(pstmt != null) pstmt.close();
		            } catch (SQLException e) {
		               e.printStackTrace();
		            }
		         
		      }
		      return member;
		   }

}
