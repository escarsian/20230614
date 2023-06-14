package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateEx {
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		System.out.println("id> ");
		String id = scn.nextLine();
		System.out.println("pw> ");
		String pw = scn.nextLine();
		System.out.println("addr> ");
		String addr = scn.nextLine();
		
		// jdbc driver 체크.
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";

		Connection conn = null;
		PreparedStatement psmt = null; // Statement stmt = null; 이거보다 더 편함.
		String sql = "update tbl_users"
				+ "   set   user_pw = ?, "  //변수에 ? 넣으면 됨.
				+ "         user_addr = ? "
				+ "   where user_id = ? ";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass); // getConnection는 정적 메소드

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw); 
			psmt.setString(2, addr);
			psmt.setString(3, id);
			
			int r = psmt.executeUpdate(); // 처리된 건수를 r로 반환.
			if (r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end of prog.");
	}
}
