package board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.Main;
import util.JDBCTemplate;

public class BoardController {
	
	//메뉴
	public void printMenu() throws Exception {
		System.out.println("-----BOARD-----");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 목록 조회");
		System.out.println("3. 게시글 상세 조회");
		System.out.print("메뉴 번호 입력 : ");
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
		case "2" : selectBoardList(); break;
		case "3" : selectBoardOne(); break;
		default: System.out.println("잘못 입력하셨습니다.");
		}
	}//method
	
	//게시글 작성 (로그인 한 사람만 진행할 수 있게)
	private void write() throws Exception {
		
		if(Main.loginMember == null) {
			System.out.println("로그인 후 이용 가능합니다.");
			return;
		}
		
		//conn
		Connection conn = JDBCTemplate.getConn();
		
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("내용 : ");
		String content = Main.SC.nextLine();
		
		//sql
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL , ? , ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, Main.loginMember.getNo() );
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 작성 실패 ... ");
			return;
		}
		System.out.println("게시글 작성 성공 ! ");
	}//method
	
	//게시글 목록 조회
	private void selectBoardList() {
		
	}
	
	//게시글 상세 조회
	private void selectBoardOne() {
		
	}

}//class



































