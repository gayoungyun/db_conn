package account.main;

import java.util.Scanner;

import account.service.MemberServiceImpl;
import account.service.Memberservice;

public class MainAccount {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Memberservice ms = new MemberServiceImpl();
		int num;
		while(true) {
			System.out.println("1.고객보기");
			System.out.println("2.특정고객보기");
			System.out.println("3.계좌개설");
			System.out.println("4.계좌삭제");
			System.out.println("5.예금");
			System.out.println("6.출금");
			System.out.println("7.종료");
			System.out.println(">>> : ");
			num = sc.nextInt();
			switch(num) {
			case 1 : ms.accountView(); break;
			case 2 : ms.accountViewOne(); break;
			case 3 : ms.accountOpen(); break;
			case 4 : ms.accountDel(); break;
			//case 5 : ms.accountDeposit(); break;
			//case 6 : ms.accountWithdraw(); break;
			case 7 : System.out.println("프로그램종료!!"); return;
			}
		}
	}

}
