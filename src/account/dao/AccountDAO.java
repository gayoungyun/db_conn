package account.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import account.dto.AccountDTO;


public class AccountDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public AccountDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String ur1 = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "java", pwd = "1234";
			con = DriverManager.getConnection(ur1, id, pwd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<AccountDTO> getMembers(){
		String sql = "select * from tbl_account1";
		ArrayList<AccountDTO> mem = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setAccountNum(rs.getString("ACCOUNTNUM"));
				dto.setCustomerName(rs.getString("CUSTOMERNAME"));
				dto.setAccountPwd(rs.getString("ACCOUNTPWD"));
				dto.setBalance(rs.getInt("BALANCE"));

				mem.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mem;
	}
	public AccountDTO memberViewOne(String userId) {
		String sql = "select * from tbl_account1 where id = '"+userId+"'";
		AccountDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new AccountDTO();
				dto.setAccountNum(rs.getString("anum"));
				dto.setCustomerName(rs.getString("name"));
				dto.setAccountPwd(rs.getString("pwd"));
				dto.setBalance(rs.getInt("balance"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public int open(AccountDTO dto) {
		String sql = "insert into tbl_account1 values(?,?,?,?)";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAccountNum());
			ps.setString(2, dto.getCustomerName());
			ps.setString(3, dto.getAccountPwd());
			ps.setInt(4, dto.getBalance());
			
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(String delId) {
		String sql = "delete from tbl_account1 where id=?";
		int result =0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, delId);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public AccountDTO memberChk(String name) {
		String sql = "select * from tbl_account1 where name=?";
		AccountDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new AccountDTO();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public AccountDTO deposit(String balance) {
		Scanner sc = new Scanner(System.in);
		int input=0;
		String sql="update tbl_account1 set balance=balance+? where name=?";
		AccountDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, input);
			ps.setString(2, balance);
			ps.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
}















