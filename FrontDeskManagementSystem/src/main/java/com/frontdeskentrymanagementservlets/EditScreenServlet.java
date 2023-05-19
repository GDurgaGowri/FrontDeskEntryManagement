package com.frontdeskentrymanagementservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String query = "select Name,Purpose,Meetingperson,Gender,Mobile,City,Date,Intime,Outtime,Email from userdetials where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='UTF-8'>");
		pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		pw.println("<title>User Data</title>");
		pw.println("<style>");
		pw.println("body {");
		pw.println("    font-family: Arial, sans-serif;");
		pw.println("    background-color: #f7f7f7;");
		pw.println("    margin: 0;");
		pw.println("}");
		pw.println("h2 {");
		pw.println("    background-color: #2980b9;");
		pw.println("    color: #fff;");
		pw.println("    text-align: center;");
		pw.println("    padding: 20px 0;");
		pw.println("    margin: 0;");
		pw.println("}");
		pw.println("table {");
		pw.println("    width: 100%;");
		pw.println("    border-collapse: collapse;");
		pw.println("}");
		pw.println("th {");
		pw.println("    background-color: #2980b9;");
		pw.println("    color: #fff;");
		pw.println("    text-align: left;");
		pw.println("    padding: 8px;");
		pw.println("}");
		pw.println("td {");

		pw.println("    text-align: left;");
		pw.println("    padding: 8px;");
		pw.println("}");
		pw.println("tr:nth-child(even) {");
		pw.println("    background-color: #f2f2f2;");
		pw.println("}");
		pw.println(".container {");
		pw.println("    max-width: 1200px;");
		pw.println("    margin: 0 auto;");
		pw.println("    padding: 20px;");
		pw.println("}");
		pw.println(".btn {");
		pw.println("    display: inline-block;");
		pw.println("    padding: 10px 20px;");
		pw.println("    background-color: #2980b9;");
		pw.println("    color: black;");
		pw.println("    text-decoration: none;");
		pw.println("    border-radius: 4px;");
		pw.println("    transition: background-color 0.3s;");
		pw.println("}");
		pw.println(".image-container {");
		pw.println("    position: fixed;");
		pw.println("    bottom: 0;");
		pw.println("    left: 0;");
		pw.println("    right: 0;");
		pw.println("    height: 200px;");
		pw.println(
				"    background-image: url('https://www.google.com/search?q=chainsys+logo&rlz=1C1ONGR_enIN1056IN1056&oq=chainsys+logo&aqs=chrome..69i57.5524j0j15&sourceid=chrome&ie=UTF-8#imgrc=1FPQHl9g4oG4rM');");
		pw.println("    background-repeat: no-repeat;");
		pw.println("    background-size: cover;");
		pw.println("}");
		pw.println(".btn:hover {");
		pw.println("    background-color: #1c638e;");
		pw.println("}");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div class='office-image'></div>");
		pw.println("<h2> Data to be edited</h2>");
		pw.println("<div class='container'>");

		int id = Integer.parseInt(req.getParameter("id"));
		// link the bootstrap

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");
				PreparedStatement ps = con.prepareStatement(query);) {
			// set value
			ps.setInt(1, id);
			// resultSet
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:1000px;height:1000px;margin-top:10px;'>");
			pw.println("<form action='edit?id=" + id + "' method='post'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='userName' value='" + rs.getString(1) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Purpose</td>");
			pw.println("<td><input type='textbox' name='purpose' value='" + rs.getString(2) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Meetingperson</td>");
			pw.println("<td><input type='textbox' name='meetingperson' value='" + rs.getString(3) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Gender</td>");
			pw.println("<td><input type='text' name='gender' value=" + rs.getString(4) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Mobile</td>");
			pw.println("<td><input type='tel' name='mobile' value='" + rs.getString(5) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>City</td>");
			pw.println("<td><input type='text' name='city' value='" + rs.getString(6) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Date</td>");
			pw.println("<td><input type='date' name='date' value='" + rs.getString(7) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Intime</td>");
			pw.println("<td><input type='time' name='intime' value='" + rs.getString(8) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Outtime</td>");
			pw.println("<td><input type='time' name='outtime' value='" + rs.getString(9) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Email</td>");
			pw.println("<td><input type='email' name='email' value='" + rs.getString(10) + "'></td>");
			pw.println("</tr>");
			pw.println("<tr>");

			pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		} catch (SQLException se) {
			pw.println("<h2 class='bg-sucess text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<button class='btn'><a href='home.html'>Home</a></button>");
		pw.println("<a href=reg.html>Edit</a>");
		pw.println("</div>");
		// close the stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
