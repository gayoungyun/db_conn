package ex01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ex01.dto.MemberDTO;

public class MemberDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String ur1= "jdbc:oracle:thin:@localhost:1521:xe";
			String id ="java", pwd = "1234";
			con = DriverManager.getConnection(ur1, id, pwd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<MemberDTO> getMembers(){
		String sql = "select * from member_test";
		ArrayList<MemberDTO> mem = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while( rs.next() ) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));

				mem.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mem; //arraylist
	}
	public MemberDTO memberChk(String id) {
		String sql = "select * from member_test where id=?";
		MemberDTO dto = null;
		//boolean사용가능
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public int modify(MemberDTO dto) {//둘중하나
		String sql = "update member_test set pwd=?, name=?, age=? where id=?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPwd());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getAge());
			ps.setString(4, dto.getId());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int modify(String id, String pwd, String name, int age) {
		System.out.println("매개변수 4개 modify 실행!");
		String sql = "update member_test set pwd=?, name=?, age=? where id=?";

		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, id);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public MemberDTO memberSelectOne(String userId) {
		String sql = "select * from member_test where id ='"+userId+"'";
		MemberDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setAge(rs.getInt("age"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public int insert(MemberDTO dto) {
		String sql = "insert into member_test values(?,?,?,?)";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getAge());
			
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(String delId) {
		String sql = "delete from member_test where id=?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, delId);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}













