package com.abc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
              HttpSession session=request.getSession();
              session.invalidate();
              responce.sendRedirect("/BankingApplication/Login.html");
	}


}
