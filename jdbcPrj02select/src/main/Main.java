package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws Exception {
		
		System.out.println("===== JDBC (select) =====");
		
		//JDBC 드라이버 로딩
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		
		//데이터베이스 연결 정보 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "1234";
		
		//데이터베이스 연결 == 커넥션 객체 얻기
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		//SQL 준비
		String sql = "SELECT * FROM MEMBER";
		
		//SQL 실행을 위한 statement 준비
		Statement stmt = conn.createStatement();
		
		//statement 에 SQL 담아주고 실행 및 결과 리턴받기
		ResultSet rs = stmt.executeQuery(sql);
		
		//결과 출력
		while( rs.next() ) {
			//데이터 꺼내기
			String id01 = rs.getString("ID");
			String pwd01 = rs.getString("PWD");
			String nick01 = rs.getString("NICK");
			
			//데이터 뭉치기
			MemberVo mvo = new MemberVo(id01, pwd01, nick01);
			
			System.out.println(mvo);
		}
		
		//사용한 자원 반납
		// ~~~~~
		
		
	}

}
