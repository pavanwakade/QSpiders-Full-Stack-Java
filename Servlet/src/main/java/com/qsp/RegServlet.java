package com.qsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("n");
		String phon=req.getParameter("p");
		
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>Name:"+name+"</h1>");
		pw.print("<h1>Phone:"+phon+"</h1>");
	}
}
