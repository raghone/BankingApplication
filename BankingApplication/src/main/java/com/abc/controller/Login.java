package com.abc.controller;

import java.io.IOException;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
private HttpSession session;

@Override
protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
	String  custid=request.getParameter("custid");
	String password =request.getParameter("password");
	session=request.getSession(true);
	
	try {
		Model m= new Model();
		m.setCustid(custid);
		m.setPassword(password);
		boolean b=m.login();
		
		if (b==true) {
			session.setAttribute("account_number", m.getAccount_number());
			responce.sendRedirect("/BankingApplication/Home.html");
			
		}
		else {
			responce.sendRedirect("/BankingApplication/Error.html");
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}

}
