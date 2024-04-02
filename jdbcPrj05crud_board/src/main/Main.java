package main;

import java.util.Scanner;

import board.BoardController;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		
		System.out.println("===== CRUD =====");
		
		BoardController bc = new BoardController();
		bc.printMenu();

	}//main

}//class
