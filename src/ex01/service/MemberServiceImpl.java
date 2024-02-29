package ex01.service;

import java.util.ArrayList;
import java.util.Scanner;

import ex01.dto.MemberDTO;
import ex01.dao.MemberDAO;

public class MemberServiceImpl implements MemberService{
	Scanner input = new Scanner(System.in);
	private MemberDAO dao;
	public MemberServiceImpl(){
		dao = new MemberDAO();
	}
	public void memberView() {
		System.out.println("회원 보기 기능");
		ArrayList<MemberDTO> members = dao.getMembers();//
		if(members.size() == 0) {
			System.out.println("등록된 정보가 없습니다!");
		}else {
			//for( MemberDTO m : members) {}
			for(int i= 0; i<members.size() ; i++){
				MemberDTO m = members.get(i);
				System.out.println("id : "+m.getId());
				System.out.println("pwd : "+m.getPwd());
				System.out.println("name : "+m.getName());
				System.out.println("age : "+m.getAge());
				System.out.println("--------------");
			}
		}
	}
	public void modify() {
		System.out.println("수정 기능");
		Scanner input = new Scanner(System.in);
		String id, pwd, name;
		int age;
		while(true) {
			System.out.println("수정할 id 입력");
			id = input.next();
			MemberDTO m = dao.memberChk(id);
			if(m != null)
				break;
			System.out.println("존재하지 않는 id 입니다..");
		}
		System.out.println("수정할 pwd");
		pwd = input.next();
		System.out.println("수정할 name");
		name = input.next();
		System.out.println("수정할 age");
		age = input.nextInt();

		MemberDTO dto = new MemberDTO();
		dto.setId(id); dto.setPwd(pwd); dto.setName(name);
		dto.setAge(age);

		//int result = dao.modify(dto);
		int result = dao.modify(id, pwd, name, age);

		if(result == 1) {
			System.out.println("수정되었습니다.");
		}else {
			System.out.println("수정실패!!");

		}
	}
	public void memberViewOne() {
		System.out.println("검색할 id 입력");
		String userId = input.next();
		MemberDTO d = dao.memberSelectOne(userId);

		if(d == null)
			System.out.println("존재하지 않는 id입니다");
		else {
			System.out.println("--- 검색결과 ---");
			System.out.println("id : "+d.getId());
			System.out.println("pwd : "+d.getPwd());
			System.out.println("name : "+d.getName());
			System.out.println("age : "+d.getAge());
		}
	}
	public void memberInsert() {
		MemberDTO dd = new MemberDTO();
		MemberDAO db = new MemberDAO();

		while(true) {
			System.out.println("가입할 id 입력");
			dd.setId(input.next());
			MemberDTO dt = db.memberSelectOne(dd.getId());
			if(dt == null)
				break;
			System.out.println("존재하는 id.. 다시입력");
		}
		System.out.println("가입할 pwd 입력");
		dd.setPwd(input.next());
		System.out.println("가입할 name입력");
		dd.setName(input.next());
		System.out.println("가입할 age 입력");
		dd.setAge(input.nextInt());

		int res = db.insert(dd);
		if(res == 1)
			System.out.println("회원가입 성공!!");
		else
			System.out.println("존재하는 id 안되");
	}
	public void memberDel() {
		MemberDAO db = new MemberDAO();
		System.out.println("삭제 id 입력");
		String delId = input.next();
		int re = db.delete(delId);
		if(re == 1) {
			System.out.println("삭제 성공!!");
		}else {
			System.out.println("존재하지 않는 아이디 삭제 불가!");
		}
	}
}









