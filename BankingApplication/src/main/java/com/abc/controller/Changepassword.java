package com.abc.controller;

import java.io.IOException;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
String password=request.getParameter("newpassword"); 

HttpSession session=request.getSession();
int account_number=(int) session.getAttribute("account_number");

try {
	Model m=new Model();
	m.setAccount_number(account_number);
	m.setPassword(password);
	
	boolean b=m.Changepassword();
	if(b==true) {
		responce.sendRedirect("/BankingApplication/ChangePasswordSuccess.html");
	}
	else {
		responce.sendRedirect("/BankingApplication/ChangePasswordFail.html");
	}
}
catch (Exception e) {
	e.printStackTrace();
}

}
}
