package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import util.JDBCTemplate;

public class MemberController {
	
	// 메뉴
	public void printMenu() throws Exception {
		System.out.println("---------------");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.print("원하는 메뉴 번호 입력 : ");
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : join(); break;
		case "2" : login(); break;
		default : System.out.println("잘못된 입력입니다.");
		}
	}//method
	
	// 회원가입
	private void join() throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConn();
		
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Main.SC.nextLine();
		System.out.print("닉네임 : ");
		String nick = Main.SC.nextLine();
		
		//sql
		String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL , ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		pstmt.setString(3, nick);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("회원가입 실패 ...");
			return;
		}
		System.out.println("회원가입 성공 !");
	}//method
	
	// 로그인
	private void login() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConn();
		
		System.out.print("아이디 : ");
		String inputId = Main.SC.nextLine();
		System.out.print("패스워드 : ");
		String inputPwd = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		MemberVo vo = null;
		if( rs.next() ) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			String quitYn = rs.getString("QUIT_YN");
			
			vo = new MemberVo(no, id, pwd, nick, enrollDate, modifyDate, quitYn);
		}
		
		if(vo == null) {
			System.out.println("로그인 실패 ...");
			return; //early exit
		}
		
		System.out.println(vo.getNick() + " 님 환영합니다 ~ ^^");
		Main.loginMember = vo;
		
	}//method
	
	

}//class
