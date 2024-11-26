package com.abc.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ChangepasswordFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String newpassword=request.getParameter("newpassword");
        String confirmpassword=request.getParameter("confirmpassword");
        if(newpassword.equals(confirmpassword)) {
        	chain.doFilter(request, response);
        }
        else {
        	((HttpServletResponse) response).sendRedirect("ChangePasswordFail.html");
        	
        }
	}


}
