package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;

public class MemberController {
	
	public void printMenu() throws Exception {
		System.out.println("----- MEMBER -----");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		
		System.out.print("번호를 입력하세요 : ");
		String input = Main.SC.nextLine();
		
		switch(input) {
		case "1" : join(); break;
		case "2" : login(); break;
		default : System.out.println("잘못입력하셨습니다.");
		}
		
	}
	
	private void join() throws Exception {
		System.out.println("----- 회원가입 -----");
		
	    Connection conn = getConn();
	    
	    //SQL 준비
	    String sql = "INSERT INTO MEMBER(ID,PWD,NICK) VALUES(?,?,?)";
	    
	    System.out.print("아이디 : ");
	    String id = Main.SC.nextLine();
	    System.out.print("패스워드 : ");
	    String pwd = Main.SC.nextLine();
	    System.out.print("닉네임 : ");
	    String nick = Main.SC.nextLine();
	    
	    //SQL 실행을 위한 statement 준비
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, id);
	    pstmt.setString(2, pwd);
	    pstmt.setString(3, nick);
	    
	    //statement 에 SQL 담아주고 실행 및 결과 리턴받기
	    int result = pstmt.executeUpdate();
	    
	    //결과 출력
	    if(result != 1) {
	    	throw new RuntimeException("회원가입 실패 ...");
	    }
	    
	    System.out.println("회원가입 성공 !");
	    
	}//method
	
	private void login() throws Exception {
		
		System.out.println("-----로그인-----");

		Connection conn = getConn();
		
	    //SQL 준비
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("패스워드 : ");
		String pwd = Main.SC.nextLine();
		
	    //SQL 실행을 위한 statement 준비
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		
	    //statement 에 SQL 담아주고 실행 및 결과 리턴받기
		ResultSet rs = pstmt.executeQuery();
		
	    //결과 출력
		if( rs.next() ) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			
			MemberVo vo = new MemberVo(dbId, dbPwd, dbNick);
			System.out.println(vo.getNick() +" 님 환영합니다 ~ !");
		}else {
			throw new RuntimeException("로그인 실패 ...");
		}
		
	}//method
	
	private Connection getConn() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		return conn;
	}

}//class

















