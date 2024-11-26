package com.abc.controller;

import java.io.IOException;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
HttpSession session=request.getSession();
 int account_number=(int) session.getAttribute("account_number");
 String samount = request.getParameter("amount");

int amount=Integer.parseInt(samount);

String sreciveraccount_number = request.getParameter("reciveraccount_number");

int reciveraccount_number=Integer.parseInt(sreciveraccount_number);
 
 try {
	 Model m=new Model();
	 m.setAccount_number(account_number);
	 m.setBalance(amount);
	 m.setReciveraccount_number(reciveraccount_number);
	 boolean b=m.transfer();
	 if(b==true) {
		 responce.sendRedirect("/BankingApplication/TransferSuccess.html");
	 }else {
		 responce.sendRedirect("/BankingApplication/TransferFail.html"); 
	 }
 }
 catch(Exception e){
	e.printStackTrace(); 
 }
}

}
