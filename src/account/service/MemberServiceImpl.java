package account.service;

import java.util.ArrayList;
import java.util.Scanner;

import account.dao.AccountDAO;
import account.dto.AccountDTO;

public class MemberServiceImpl implements Memberservice{
	Scanner sc = new Scanner(System.in);
	private AccountDAO dao;
	public MemberServiceImpl() {
		dao = new AccountDAO();
	}
	public void accountView() {
		System.out.println("고객정보 보기");
		ArrayList<AccountDTO> members = dao.getMembers();
		if(members.size() == 0) {
			System.out.println("등록된 정보가 없습니다!!");
		}else {
			for( int i = 0; i<members.size(); i++) {
				AccountDTO m = members.get(i);
				System.out.println("anum : "+m.getAccountNum());
				System.out.println("name : "+m.getCustomerName());
				System.out.println("pwd : "+m.getAccountPwd());
				System.out.println("balance : "+m.getBalance());
				System.out.println("------------");
			}
		}
	}
	public void accountViewOne() {
		System.out.println("검색할 고객번호 입력");
		String userId = sc.next();
		AccountDTO d = dao.memberViewOne(userId);

		if(d == null)
			System.out.println("존재하지 않는 id 입니다");
		else {
			System.out.println("--- 검색 결과 ---");
			System.out.println("anum : "+d.getAccountNum());
			System.out.println("name : "+d.getCustomerName());
			System.out.println("pwd : "+d.getAccountPwd());
			System.out.println("balance : "+d.getBalance());
		}
	}
	public void accoutOpen() {
		AccountDTO d = new AccountDTO();
		AccountDAO db = new AccountDAO();
		
		while(true) {
			System.out.println("가입할 고객번호 입력");
			d.setAccountNum(sc.next());
			AccountDTO dto = db.memberViewOne(d.getAccountNum());
			if(dto == null)
				break;
			System.out.println("존재하는 번호 ..다시입력");
		}
		System.out.println("개설할 name 입력");
		d.setCustomerName(sc.next());
		System.out.println("개설할 pwd 입력");
		d.setAccountPwd(sc.next());
		System.out.println("입금 금액 입력");
		d.setBalance(sc.nextInt());
		
		int res = db.open(d);
		if(res == 1)
			System.out.println("개설 성공!");
		else
			System.out.println("개설 실패!");
	}
	public void accountDel() {
		AccountDAO db = new AccountDAO();
		System.out.println("삭제 고객번호 입력");
		String delId = sc.next();
		int re = db.delete(delId);
		if(re == 1) {
			System.out.println("삭제 성공!");
		}else {
			System.out.println("존재하지 않는 고객번호!");
		}
	}
	/*
	public void accountDeposit() {
		System.out.println("예금");
		String anum, name, pwd;
		int balance;
		while(true) {
			System.out.println("예금넣을 고객이름");
			balance = sc.nextInt();
			AccountDTO m = dao.memberChk(name);
			if(m != null)
				break;
			System.out.println("존재하지 않는 고객");
		}
		System.out.println("예금 금액 입력");
		balance = sc.nextInt();
		AccountDTO dto = new AccountDTO();
		dto.setBalance(balance);
		
		int result = dao.accountDeposit(balance);
		
		if(result == 1) {
			System.out.println("예금 : "+balance);
		}else {
			System.out.println("예금실패!!");
		}
	}
	*/
	@Override
	public void accountOpen() {
		
	}
}













