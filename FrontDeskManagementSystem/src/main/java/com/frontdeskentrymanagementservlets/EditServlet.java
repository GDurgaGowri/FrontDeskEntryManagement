package com.frontdeskentrymanagementservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String query = "update userdetials set Name=?,Purpose=?,Meetingperson=?,Gender=?,Mobile=?,City=?,Date=?,Intime=?,Outtime=?,Email=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<a href='showuser.html'>Edit</a>");
		// get the values
		int id = Integer.parseInt(req.getParameter("id"));
		String Name = req.getParameter("userName");
		String Purpose = req.getParameter("purpose");
		String Meetingperson = req.getParameter("meetingperson");
		String Gender = req.getParameter("gender");
		String Mobile = req.getParameter("mobile");
		String City = req.getParameter("city");
		String Date = req.getParameter("date");
		String Intime = req.getParameter("intime");
		String Outtime = req.getParameter("outtime");
		String Email = req.getParameter("email");
		// load the JDBC driver

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");
				PreparedStatement ps = con.prepareStatement(query);) {
			// set the values
			ps.setString(1, Name);
			ps.setString(2, Purpose);
			ps.setString(3, Meetingperson);
			ps.setString(4, Gender);
			ps.setString(5, Mobile);
			ps.setString(6, City);
			ps.setString(7, Date);
			ps.setString(8, Intime);
			ps.setString(9, Outtime);
			ps.setString(10, Email);
			ps.setInt(11, id);
			// execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if (count == 1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Edited Successfully</h2>");
			} else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Not Edited</h2>");
			}
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
		pw.println("</div>");
		// close the stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
