package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class JoinMain {

	public static void main(String[] args) throws Exception {
		
		System.out.println("===== JDBC (pstmt) =====");
		
		//JDBC 드라이버 로딩
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		
		//데이터베이스 연결 정보 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "1234";
		
		//데이터베이스 연결 == 커넥션 객체 얻기
		Connection conn = DriverManager.getConnection(url , id, pwd);
		
		//유저입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String inputId = sc.nextLine();
		System.out.print("패스워드 : ");
		String inputPwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String inputNick = sc.nextLine();
		
		//SQL 준비
		String sql = "INSERT INTO MEMBER(ID,PWD,NICK) VALUES( ?, ?, ? )";
		
		//SQL 실행을 위한 statement 준비 및 쿼리 완성 (==물음표 채우기)
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		pstmt.setString(3, inputNick);
		
		//statement 에 SQL 담아주고 실행 및 결과 리턴받기
		int result = pstmt.executeUpdate();
		
		//결과 출력
		System.out.println("쿼리 실행 결과 : " + result);
		
		//사용한 자원 반납
		// ~~~~~

	}

}
