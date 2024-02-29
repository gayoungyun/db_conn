package ex01.main;

import java.util.Scanner;

import ex01.service.MemberService;
import ex01.service.MemberServiceImpl;

public class MainClass {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		MemberService ms = new MemberServiceImpl();
		int num;
		while(true) {
			System.out.println("1.회원보기");
			System.out.println("2.회원수정");
			System.out.println("3.특정회원보기");
			System.out.println("4.데이터 추가");
			System.out.println("5.데이터 삭제");
			System.out.println("6.종료");
			System.out.println(">>> : ");
			num= input.nextInt();
			switch(num) {
			case 1: ms.memberView(); break;
			case 2: ms.modify(); break;
			case 3: ms.memberViewOne(); break;
			case 4: ms.memberInsert(); break;
			case 5: ms.memberDel(); break;
			case 6: System.out.println("프로그램 종료!!"); return;
			}
		}
	}

}
