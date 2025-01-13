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

//@WebServlet("/valid")
public class Validation extends HttpServlet {
	
	private Connection con=null;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter writer=resp.getWriter();
		
		String name=req.getParameter("username");
		String password=req.getParameter("password");
		try
		{
			String Query="select * from tapstudent where name=? and pwd=?";
			PreparedStatement stmt=con.prepareStatement(Query);
			stmt.setString(1, name);
			stmt.setString(2, password);
			ResultSet res=stmt.executeQuery();
			if(res.next()==true)
			{
				writer.println("welcome to tap academy");
				req.getRequestDispatcher("drive").include(req, resp);
			}
			else
			{
				writer.println("welcome to tapacademy drive");
				req.getRequestDispatcher("invalid.html").include(req, resp);
			}
			
					
			
		}
		catch(Exception e)
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
