package com.frontdeskentrymanagementservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SoftdeleteServlet {
	private static final String qu = "insert into alluserdetials(Name,Purpose,Meetingperson,Gender,Mobile,City,Date,Intime,Outtime,Email) values(?,?,?,?,?,?,?,?,?,?)";

	public static boolean insertData(String Name, String Purpose, String Meetingperson, String Gender, String Mobile,
			String City, String Date, String Intime, String Outtime, String Email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");

				PreparedStatement ps = con.prepareStatement(qu);) {
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
			// execute the query
			int count = ps.executeUpdate();
			return count == 1;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
