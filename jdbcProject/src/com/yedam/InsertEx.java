package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertEx {
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		System.out.println("id> ");
		String id = scn.nextLine();
		System.out.println("pw> ");
		String pw = scn.nextLine();
		System.out.println("name> ");
		String name = scn.nextLine();
		
		// jdbc driver 체크.
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";

		Connection conn = null;
		Statement stmt = null;
		String sql = "insert into tbl_users (user_id, user_pw, user_name)" + 
					 "values('" + id + "', '" + pw + "', '" + name + "')";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass); // getConnection는 정적 메소드
			stmt = conn.createStatement(); // 여기서는 자동 commit됨.
			int r = stmt.executeUpdate(sql); // insert, update, delete가 처리된 건수만큼 r로 반환.
			if (r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}
		} catch (Exception e) {
			System.out.println("처리 중 에러발생.");
			e.printStackTrace();
		} finally { // 성공하든 예외발생하든 반드시 실행.
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end of prog.");
	}
}
