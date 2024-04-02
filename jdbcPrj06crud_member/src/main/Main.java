package main;

import java.util.Scanner;

import board.BoardController;
import member.MemberController;
import member.MemberVo;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember = null;	//로그인한 유저정보를 담아둘 변수

	public static void main(String[] args) throws Exception {
		
		MemberController mc = new MemberController();
		BoardController bc = new BoardController();
		
		System.out.println("===== CRUD =====");
		
		while(true) {
			System.out.println("1. MEMBER");
			System.out.println("2. BOARD");
			System.out.println("9. 종료하기");
			System.out.print("원하는 번호 입력 : ");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : mc.printMenu(); break;
			case "2" : bc.printMenu(); break;
			case "9" : System.out.println("프로그램 종료"); return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}//main

}//class














