package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.abc.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int account_number=(int) session .getAttribute("account_number");
        
        try {
        	Model m=new Model();
        	m.setAccount_number(account_number);
        	ArrayList al=m.getStatement();
        	
            if(al.isEmpty()==true) {
            	responce.sendRedirect("/BankingApplication/Statementfail.html");
            }
            else {
            	session.setAttribute("sal",m.sal );
            	session.setAttribute("ral", m.ral);
            	session.setAttribute("al", m.al);
            	responce.sendRedirect("/BankingApplication/StatementSuccess.jsp");
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	


}
