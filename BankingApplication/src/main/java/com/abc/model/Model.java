package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.ConnectionEvent;

public class Model {
private String name;
private String custid;
private int account_number;
private String password;
private int balance;
private String email;
private int reciveraccount_number;

private Connection con;
private PreparedStatement pstmt;
private ResultSet res;
public ArrayList al=new ArrayList();
public ArrayList sal=new ArrayList();
public ArrayList ral=new ArrayList();


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCustid() {
	return custid;
}
public void setCustid(String custid) {
	this.custid = custid;
}
public int getAccount_number() {
	return account_number;
}
public void setAccount_number(int account_number) {
	this.account_number = account_number;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getReciveraccount_number() {
	return reciveraccount_number;
}
public void setReciveraccount_number(int reciveraccount_number) {
	this.reciveraccount_number = reciveraccount_number;
}

public Model() throws Exception {
Class.forName("com.mysql.jdbc.Driver");// Loading the driver
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication", "root", "@Danjrvlogs1");
System.out.println("Loading the driver and establishing the coonection is completed");
}

public boolean register() throws SQLException {
	String s="insert into ABCBank values(?,?,?,?,?,?)";//incomplete query
	pstmt=con.prepareStatement(s);
	pstmt.setString(1, name);
	pstmt.setString(2, custid);
	pstmt.setInt(3, account_number);
	pstmt.setString(4, password);
	pstmt.setInt(5, balance);
	pstmt.setString(6, email);
	
	int x=pstmt.executeUpdate();
	
	if(x>0) {
		return true;
	}
	return false;
}
public boolean login() throws SQLException {
	String s="select * from ABCBank where custid=? and password=? ";
	pstmt=con.prepareStatement(s);
	pstmt.setString(1, custid);
	pstmt.setString(2, password);
	
	ResultSet res=pstmt.executeQuery();
	
	while (res.next()==true) {
		account_number=res.getInt("account_number");
		return true;
	}
	return false;
}
public boolean checkBalance() throws SQLException {
	String s="select balance from ABCBank where account_number=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, account_number);
	
	ResultSet res=pstmt.executeQuery();
	
	while (res.next()==true) {
		balance=res.getInt("Balance");
		return true;
	}
	return false;
}
public boolean Changepassword() throws SQLException {
  String s="update ABCBank set password=? where account_number=?";
  pstmt=con.prepareStatement(s);
  pstmt.setString(1, password);
  pstmt.setInt(2, account_number);
  
  int x=pstmt.executeUpdate();
  
  if(x>0) {
	  return true;
  }
	return false;
}
public boolean transfer() throws SQLException {
	String s1="select * from ABCBank where account_number=?";
	pstmt=con.prepareStatement(s1);
	pstmt.setInt(1, reciveraccount_number);
	ResultSet res = pstmt.executeQuery();
	while(res.next()==true) {
		String s2="update ABCBank set balance=balance-? where account_number=?";
		pstmt =con.prepareStatement(s2);
		pstmt.setInt(1,balance );
		pstmt.setInt(2, account_number);
		int y1=pstmt.executeUpdate();
		if(y1>0) {
			int x=res.getInt("BALANCE");
			if(x>0) {
				String s3="update ABCBank set balance=balance+? where account_number=?";
				pstmt =con.prepareStatement(s2);
				pstmt.setInt(1,balance );
				pstmt.setInt(2, reciveraccount_number);
				int y2=pstmt.executeUpdate();
				if(y2>0) {
					String s4="insert into GetStatement values(?,?,?)";
					pstmt=con.prepareStatement(s4);
					pstmt.setInt(1,account_number );
					pstmt.setInt(2,reciveraccount_number );
					pstmt.setInt(3,balance );
					int y=pstmt.executeUpdate();
					if(y>0) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		
	}
	
	return false;
}
public ArrayList getStatement() throws SQLException {
	String s="select * from GetStatement where account_number=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, account_number);
	 res = pstmt.executeQuery();
	 
	 while(res.next()==true) {
		 sal.add(res.getInt("account_number"));
		 ral.add(res.getInt("reciveraccount_number"));
		al.add(res.getInt("balance"));
		
	 }
	
	
	return al;
}
public boolean applyLoan() throws SQLException {
	String s="select * from ABCBank where account_number=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, account_number);
	 res = pstmt.executeQuery();
	 while(res.next()==true) {
		 name=res.getString("name");
		email= res.getString("email");
		 return true;
		 
	 }
	return false;
}



}
