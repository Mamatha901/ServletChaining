package com.tap.students;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/drive")
public class Drive extends HttpServlet {
	private Connection con;

    private String url = "jdbc:mysql://localhost:3306/tapacademy";
    private String un = "root";
    private String pwd = "mammu@4556";
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(url, un, pwd);
	        } catch (Exception e) {
	            throw new ServletException("Database connection initialization failed", e);
	        }
		
	  }
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
	ServletException, IOException {
		// TODO Auto-generated method stub
		
		
			PrintWriter writer = resp.getWriter();
			
			try {
			String query2 = "select * from drive";
			Statement stmt = con.createStatement();
			ResultSet res2 = stmt.executeQuery(query2);
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
			int ten = res2.getInt(3);
			int twe = res2.getInt(4);
			int grad = res2.getInt(5);
			String profile = res2.getString(6);
			float pac = res2.getFloat(7);
			String skills = res2.getString(8);
			writer.println("<tr>\r\n"
							+ " <td>" + id + "</td>\r\n"
							+ " <td>" + name + "</td>\r\n"
							+ " <td>" + ten + "</td>\r\n"
							+ " <td>" + twe + "</td>\r\n"
							+ " <td>" + grad + "</td>\r\n"
							+ " <td>" + profile + "</td>\r\n"
							+ " <td>" + pac + "</td>\r\n"
							+ " <td>" + skills + "</td>\r\n"
							+ " </tr>");
		    }
			writer.println("</table>");
			req.getRequestDispatcher("/eligible").include(req, resp);
			

			} 
			catch (Exception e)
			{
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