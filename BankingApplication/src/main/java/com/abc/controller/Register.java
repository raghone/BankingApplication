package com.abc.controller;

import java.io.IOException;
import java.net.http.HttpClient;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
String name =request.getParameter("name");
String  custid=request.getParameter("custid");

String saccount_number =request.getParameter("account_number");//collects the data in form of string
int account_number= Integer.parseInt(saccount_number);//convert string data to int data

String password =request.getParameter("password");

String sbalance =request.getParameter("balance");//collects the data in form of string
int balance = Integer.parseInt(sbalance);//convert string data to int data

String email =request.getParameter("email");


try {
	Model m=new Model();
	m.setName(name);
	m.setCustid(custid);
	m.setAccount_number(account_number);
	m.setPassword(password);
	m.setBalance(balance);
	m.setEmail(email);
	
	boolean b=m.register();
	
	if(b==true) {
		responce.sendRedirect("/BankingApplication/SuccessRegister.html");
	}
	else {
		responce.sendRedirect("/BankingApplication/FailureRegister.html");
	}
}
catch (Exception e){
	e.printStackTrace();
}
}
}
