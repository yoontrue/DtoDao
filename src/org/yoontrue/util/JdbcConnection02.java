package org.yoontrue.util;

import java.sql.Connection;

public class JdbcConnection02 {

	public static void main(String[] args) {
		System.out.println("H2 데이터베이스 연결");
		Connection conn = JdbcUtil.getConnection();
		System.out.println(conn);
		
		JdbcUtil.close(conn);
		System.out.println("connection closed  .");

	}

}
