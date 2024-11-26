package com.abc.controller;

import java.io.IOException;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
 HttpSession session=request.getSession();
 int account_number=(int) session.getAttribute("account_number");
 
 try {
	 Model m=new Model();
	 m.setAccount_number(account_number);
	 
	 boolean b=m.checkBalance();
	 
	 if(b==true) {
		 session.setAttribute("balance", m.getBalance());
		 responce.sendRedirect("/BankingApplication/BalanceView.jsp");
	 }
	 else {
		 responce.sendRedirect("/BankingApplication/BalanceFail.html");
	 }
 }
 catch (Exception e){
	 e.printStackTrace();
 }
}

}