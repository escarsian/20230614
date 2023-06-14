package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteEx {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("id> ");
		String id = scn.nextLine();

//		// jdbc driver 체크.
//		String url = "jdbc:oracle:thin:@localhost:1521/xe";
//		String user = "proj";
//		String pass = "proj";

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "delete from tbl_users where user_id = ?";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = Dao.getConnect();//

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			int r = psmt.executeUpdate();
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
