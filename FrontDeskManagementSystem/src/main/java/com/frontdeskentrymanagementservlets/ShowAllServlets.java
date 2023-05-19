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

@WebServlet("/showall")
public class ShowAllServlets extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String query = "select ID,Name,Purpose,Meetingperson,Gender,Mobile,City,Date,Intime,Outtime,Email from alluserdetials";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		pw.println("<!DOCTYPE html>");
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

		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h2>Entered Person Data</h2>");
		pw.println("<button class='btn'><a href='home.html'>Home</a></button>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");
				PreparedStatement ps = con.prepareStatement(query);) {
			// resultSet
			ResultSet rs = ps.executeQuery();
			pw.println("<div style='margin:40px;width:950px;margin-top:50px;'>");
			pw.println("<table class='table table-hover table-striped' style='width:950px;'>");
			pw.println("<tr>");
			pw.println("<th>Id</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Purpose</th>");
			pw.println("<th>Meetingperson</th>");
			pw.println("<th>Gender</th>");
			pw.println("<th>Mobile</th>");
			pw.println("<th>City</th>");
			pw.println("<th>Date</th>");
			pw.println("<th>In Time</th>");
			pw.println("<th>Out Time</th>");
			pw.println("<th>Email</th>");

			pw.println("</tr>");
			while (rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getString(4) + "</td>");
				pw.println("<td>" + rs.getString(5) + "</td>");
				pw.println("<td>" + rs.getString(6) + "</td>");
				pw.println("<td>" + rs.getString(7) + "</td>");
				pw.println("<td>" + rs.getString(8) + "</td>");
				pw.println("<td>" + rs.getString(9) + "</td>");
				pw.println("<td>" + rs.getString(10) + "</td>");
				pw.println("<td>" + rs.getString(11) + "</td>");

				pw.println("</tr>");
			}
			pw.println("</table>");

		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		pw.println(
				"<img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTXYCoSzf1HyCWpLmlnRbBMUSsLRvGCc-us8975hpaFw&s.jpg\" style=\\\" height:1000px;\">");

		pw.println(
				"<img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY26XokUNODi-x_Mu5-WsM7Byun77cHf3o-WVXt-cxMvJ8h3NEJz7WtUEaYFhKyQOSEOM&usqp=CAU.jpg\" style=\\\" height=180px;\" \">");
		pw.println(
				"<img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJULZkTjs2F2KTal-tfqIuRibP1kcG41jK-g&usqp=CAU.jpg\" style=\\\" height=180px;width=100px;\" \">");
		pw.println(
				"<img src=\"https://thumbs.dreamstime.com/b/male-silhouettes-icons-7707670.jpg.jpg\" style=\\\" height=180px;\" \">");

		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
