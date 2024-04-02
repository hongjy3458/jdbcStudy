package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;

public class BoardController {
	
	// 메뉴
	public void printMenu() throws Exception {
		System.out.println("----- BOARD -----");
		
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 수정 (제목)");
		System.out.println("3. 게시글 수정 (내용)");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 게시글 목록 조회");
		System.out.println("6. 게시글 상세 조회");
		System.out.println("7. 게시글 검색 (제목)");
		System.out.println("8. 게시글 검색 (내용)");
		
		System.out.print("메뉴 번호 입력 : ");
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : write(); break;
		case "2" : editTitle(); break;
		case "3" : editContent(); break;
		case "4" : delete(); break;
		case "5" : selectBoardList(); break;
		case "6" : selectBoardOne(); break;
		case "7" : searchByTitle(); break;
		case "8" : searchByContent(); break;
		default : System.out.println("잘못된 입력입니다.");
		}
		
	}//method
	
	// 게시글 작성
	private void write() throws Exception {
		//conn 준비
		Connection conn = getConn();
		
		//sql 준비
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT) VALUES(SEQ_BOARD_NO.NEXTVAL , ? , ?)";
		
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("내용: ");
		String content = Main.SC.nextLine();
		
		//sql 실행
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		int result = pstmt.executeUpdate();
		
		//결과 처리
		if(result != 1){
			System.out.println("게시글 작성 실패 ...");
			return;
		}
		System.out.println("게시글 작성 성공 !");
	}//method

	// 게시글 수정 (제목 변경 - 번호이용해서)
	private void editTitle() throws Exception {
		//conn
		Connection conn = getConn();
		
		System.out.print("수정할 게시글 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 제목 : ");
		String title = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE BOARD SET TITLE = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		//결과
		if(result != 1) {
			System.out.println("게시글 수정 실패 ...");
			return;
		}
		System.out.println("게시글 수정 성공!");
	}

	// 게시글 수정 (내용 변경 - 번호이용해서)
	private void editContent() throws Exception {
		
		//conn
		Connection conn = getConn();
		
		System.out.print("게시글 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 내용 : ");
		String content = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE BOARD SET CONTENT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 수정 실패 ...");
			return;
		}
		System.out.println("게시글 수정 성공 !");
	}

	// 게시글 삭제
	private void delete() throws Exception {
		//conn
		Connection conn = getConn();
		
		System.out.print("삭제할 게시글 번호 :");
		String no = Main.SC.nextLine();
		
		//sql
		String sql = "DELETE BOARD WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 삭제 실패 ...");
			return;
		}
		System.out.println("게시글 삭제 성공 !");
	}

	// 게시글 목록 조회 (최신순)
	private void selectBoardList() throws Exception {
		
		//conn
		Connection conn = getConn();
		
		//sql
		String sql = "SELECT NO , TITLE , TO_CHAR(ENROLL_DATE, 'MM/DD HH:MI') ENROLL_DATE FROM BOARD ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			BoardVo vo = new BoardVo(no, title, null, enrollDate);
			voList.add(vo);
		}
		
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("작성일시");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.println();
		
		for (BoardVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getEnrollDate());
			System.out.print(" | ");
			System.out.print(vo.getTitle());
			System.out.println();
		}
		
	}

	// 게시글 상세 조회 (번호 이용해서)
	private void selectBoardOne() throws Exception {
		
		//conn
		Connection conn = getConn();
		
		System.out.print("상세조회할 게시글 번호 : ");
		String num = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM BOARD WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		BoardVo vo = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content  = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			vo = new BoardVo(no, title, content, enrollDate);
		}
		
		if(vo == null) {
			System.out.println("게시글 상세조회 실패 ...");
			return;
		}
		
		System.out.println("게시글 상세 조회 성공 !");
		System.out.println("-------------");
		System.out.println("번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성일시 : " + vo.getEnrollDate());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("-------------");
		
	}//class

	// 게시글 검색 (제목으로)
	private void searchByTitle() throws Exception {
		
		//conn
		Connection conn = getConn();
		
		System.out.print("검색할 제목 : ");
		String value = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM BOARD WHERE TITLE LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			BoardVo vo = new BoardVo(no, title, null, enrollDate);
			voList.add(vo);
		}
		
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("작성일시");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.println();
		
		for (BoardVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getEnrollDate());
			System.out.print(" | ");
			System.out.print(vo.getTitle());
			System.out.println();
		}
		
	}

	// 게시글 검색 (내용으로)
	private void searchByContent() throws Exception {
		//conn
		Connection conn = getConn();
		
		System.out.print("검색할 제목 : ");
		String value = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM BOARD WHERE CONTENT LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			BoardVo vo = new BoardVo(no, title, null, enrollDate);
			voList.add(vo);
		}
		
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("작성일시");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.println();
		
		for (BoardVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getEnrollDate());
			System.out.print(" | ");
			System.out.print(vo.getTitle());
			System.out.println();
		}
	}

	// conn 객체 얻기
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










