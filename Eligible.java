package com.tap.students;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Eligible")
public class Eligible extends HttpServlet {
	
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/tapacademy";
	String un = "root";
	String pwd = "mammu@4556";
	int ten;
	int twe;
	int grad;
			
	@Override
	public void init() throws ServletException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, un, pwd);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		try {
		String query1 = "select * from tapstudent where name = ? and pwd = ?";
		PreparedStatement pstmt1 = con.prepareStatement(query1);
		pstmt1.setString(1, username);
		pstmt1.setString(2, password);
		ResultSet res1 = pstmt1.executeQuery();
		
		if(res1.next())
		{
			ten = res1.getInt(3);
			twe = res1.getInt(4);
			 grad = res1.getInt(5);
		}
		
		String query2 = "select * from drive where 10th <= ? "+ "and 12th <= ? and grad <= ?";
		PreparedStatement pstmt2 = con.prepareStatement(query2);
		pstmt2.setInt(1, ten);
		pstmt2.setInt(2, twe);
		pstmt2.setInt(3, grad);
		ResultSet res2 = pstmt2.executeQuery();
		writer.println("<h3>"+res1.getString(2)+""
		+ "drives you are eligible for:</h3>");
		writer.println("<table border=\"1\">\r\n"
				+ "\r\n"
				+ " <tr>\r\n"
				+ " <th>Id</th>\r\n"
				+ " <th>Name</th>\r\n"
				+ " <th>10th</th>\r\n"
				+ " <th>12th</th>\r\n"
				+ " <th>Grad</th>\r\n"
				+ " <th>Profile</th>\r\n"
				+ " <th>Package</th>\r\n"
				+ " <th>Skills</th> \r\n"
				+ " </tr>");
		while(res2.next()==true){
			int id = res2.getInt(1);
			String name = res2.getString(2);
			int ten1 = res2.getInt(3);
			int twe1 = res2.getInt(4);
			int grad1 = res2.getInt(5);
			String profile = res2.getString(6);
			String pac = res2.getString(7);
			String skills = res2.getString(8);
			writer.println("<tr>\r\n"
			+ " <td>" + id + "</td>\r\n"
			+ " <td>" + name + "</td>\r\n"
			+ " <td>" + ten1 + "</td>\r\n"
			+ " <td>" + twe1 + "</td>\r\n"
			+ " <td>" + grad1 + "</td>\r\n"
			+ " <td>" + profile + "</td>\r\n"
			+ " <td>" + pac + "</td>\r\n"
			+ " <td>" + skills + "</td>\r\n"
			+ " </tr>");
			}
			writer.println("</table>");
		} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
@Override
public void destroy() {
	// TODO Auto-generated method stub
	try {
        if (con != null) {
            con.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
	
}

}
