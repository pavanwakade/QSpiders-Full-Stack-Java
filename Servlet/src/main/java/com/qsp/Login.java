package com.qsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	String idss = "pavan";
	String password = "123";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ids = req.getParameter("id");
		String passw = req.getParameter("pass");

		PrintWriter pw = resp.getWriter();

		if (ids != null && passw != null) {

			if (idss.equals(ids) && password.equals(passw)) {
				System.out.println("Login Sussess");
				pw.print("<h1><center>Login Sussess</center></h1>");
//			resp.sendRedirect("reg.html"); 

			} else {
				System.out.println("invalid credential");
				pw.print("<h1><center>invalid credential please try again</center></h1>");
			}
		}
	}
}
