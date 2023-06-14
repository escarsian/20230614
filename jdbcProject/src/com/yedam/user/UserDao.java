package com.yedam.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

// 입력, 수정, 삭제, 목록
public class UserDao {
	// 공통으로 들어가는 필드값.
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	private void close() {
		try {
			if (conn != null) {//
				conn.close();
			}
			if (psmt != null) {//
				psmt.close();
			}
			if (rs != null) {//
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 추가.
	public boolean add(UserVO user) {
		sql = "insert into tbl_users (user_id, user_pw, user_name)" + "values(?,?,?) ";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql); // new UserDao(); -> 이런 식으로 객체 생성했지만 이렇게도 가능.
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());
			psmt.setString(3, user.getUserName());

			int r = psmt.executeUpdate(); // //insert일 땐 executeUpdate 쿼리 실행.
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 조회
	public UserVO search(String userId) {
		sql = "select * from tbl_users where user_id = ?";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery(); //select 일 땐 executeQuery 
			if(rs.next()) {
				UserVO user = new UserVO();
				user.setUserId(rs.getString("user_id")); //쿼리한 값을 setUserId에 넣겠다.
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserAddr(rs.getString("user_addr"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return null; //조회된 결과가 없으면 null 반환.

	}
	//수정.
	public boolean modify(UserVO user) {
		sql = "update tbl_users"
	+ " set user_pw = nvl(?,user_pw)," // 기존의 값이 널이면 ,뒤에 값으로.
	+ " user_name = nvl(?,user_name)," 
	+ " user_birth = nvl(?,user_birth),"
	+ " user_phone = nvl(?,user_phone),"
	+ " user_addr = nvl(?,user_addr) "
	+ "where user_id= ?";
		
		conn =Dao.getConnect();
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, user.getUserPw());
			psmt.setString(2, user.getUserName());
			psmt.setString(3, user.getUserBirth());
			psmt.setString(4, user.getUserPhone());
			psmt.setString(5, user.getUserAddr());
			psmt.setString(6, user.getUserId());
			
			int r = psmt.executeUpdate(); //쿼리 실행.
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	//삭제.
	public boolean remove(UserVO user) {
		sql = "delete from tbl_users "
			+ "where user_id = ?";
		
		conn = Dao.getConnect();
		try {
			conn.setAutoCommit(false);
			conn.commit();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	//목록.
	public List<UserVO> list() {
		List<UserVO> list = new ArrayList<>();
		
		sql = "select * from tbl_users"; //조건 추가 가능.
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
					
			while(rs.next()) { //조회 건수만큼 반복.
				UserVO user = new UserVO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserAddr(rs.getString("user_addr"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
}
