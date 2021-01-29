package org.yoontrue.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {

	public static void main(String[] args) {
			try {
				Class.forName("org.h2.Driver");
				System.out.println("드라이버 검색 성공!");
				// DB서버가 실행중이어야 접속가능 하다.
				String url = "jdbc:h2:tcp://localhost/~/test";
				String user = "sa";
				String password = "12345";
				Connection conn = DriverManager.getConnection(url, user, password);
				System.out.println(conn);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 검색 실패!");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("접속실패");
				e.printStackTrace();
			}
	}
}
