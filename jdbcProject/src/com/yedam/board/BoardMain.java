package com.yedam.board;

import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		
		BoardDao dao = new BoardDao();
		Scanner scn = new Scanner(System.in);
		int menu =0;
		
		while(true) {
			System.out.println("1.등록 2.삭제 3.내용 수정 4.목록 5.상세보기 6.종료");
			System.out.println("선택> ");
			menu = scn.nextInt();
			scn.nextLine();
			if(menu==1) {
			System.out.println("title> ");
			String title = scn.nextLine();
			System.out.println("writer> ");
			String writer = scn.nextLine();
			System.out.println("content> ");
			String content = scn.nextLine();
			
			BoardVO board = new BoardVO();
			board.setBrdTitle(title);
			board.setBrdWriter(writer);
			board.setBrdContent(content);
			
			if(dao.add(board)) {
				System.out.println("등록 성공.");
			}else {
				System.out.println("등록 실패.");
			}
				
			}else if(menu==2) {
				
				
			}else if(menu==3) {
				
				
			}else if(menu==4) {
				
				
			}else if(menu==5) {
				break;
				
			}else if(menu==6) {
				break;
			}
			
		}
		
		
	}
}
