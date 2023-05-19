package com.frontdeskentrymanagementservlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/totalrecords")
public class TotalRecordsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Total Records</title>");

		out.println("<style>");
		out.println("h2 {");
		out.println("    background-color: #4CAF50;");
		out.println("    color: #fff;");
		out.println("    text-align: center;");
		out.println("    padding: 20px 0;");
		out.println("    margin: 0;");
		out.println("}");
		out.println("table { border-collapse: collapse; margin:400px;width:950px;margin-top:50px; width: 400px; }");
		out.println("th, td { text-align: left; padding: 8px; }");
		out.println("th { background-color: #4CAF50; color: white; }");
		out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
		out.println("</style></head><body>");
		out.println("<h2>Daily Records</h2>");
		out.println("<button class='btn'><a href='home.html'>Home</a></button>");
		out.println("<table>");
		out.println("<tr><th>Date</th><th>No. of Records</th></tr>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Date, COUNT(*) AS count FROM alluserdetials GROUP BY Date");

			while (rs.next()) {
				String date = rs.getString("Date");
				int count = rs.getInt("count");
				out.println("<tr><td>" + date + "</td><td>" + count + "</td></tr>");
			}

			conn.close();
		} catch (Exception e) {
			out.println(e.getMessage());
		}

		out.println("</table>");

		out.println("</body></html>");
	}
}
