package com.yedam.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yedam.Dao;

public class BoardDao {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	private void close() {
		try {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}if(rs != null) {
				rs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(BoardVO board) {
		sql = "insert into tbl_board (brd_no, brd_title, brd_writer, brd_content)"
				+ "values(board_seq.nextval,?,?,?)";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBrdTitle());
			psmt.setString(2, board.getBrdWriter());
			psmt.setString(3, board.getBrdContent());
			
			
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
	
	
	
	
	
	}
