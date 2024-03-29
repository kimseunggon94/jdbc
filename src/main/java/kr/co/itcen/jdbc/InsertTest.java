package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		insert("경영지원팀");
		insert("경영지원팀2");
		insert("경영지원팀3");
		
	}
	
	public static boolean insert(String name) {
		boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");			//이 부분만 수정해주면 된다

			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.118:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

			//3. Statement 객체생성(받아오기)
			stmt = connection.createStatement();
			
			//4. SQL문 실행
			String sql = "insert into department values(null, '"+name+ "')" ;
			int count = stmt.executeUpdate(sql);
			
			result = (count == 1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:"+e);
		} catch (SQLException e) {
			System.out.println( "error:"+e);
		} finally {
			try {
				if(stmt !=null) {
					stmt.close();
				}
				
				if(connection !=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
