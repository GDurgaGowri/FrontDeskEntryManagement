package com.frontdeskentrymanagementservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registr")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println("<button class='btn'><a href='home.html'>Home</a></button>");

		pw.println("<div class='container'>");
		pw.println(
				"<div class='image-container' href='https://www.google.com/search?q=chainsys+logo&rlz=1C1ONGR_enIN1056IN1056&oq=chainsys+logo&aqs=chrome..69i57.5524j0j15&sourceid=chrome&ie=UTF-8#imgrc=1FPQHl9g4oG4rM'></div>");

		pw.println(
				"<link rel='stylesheet' href='ttps://fonts.googleapis.com/css?family=Roboto:300,400,500,700'></link>");
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
		LocalTime currentTime = LocalTime.now();

		// Check if the intime is before the current time
		if (LocalTime.parse(Intime).equals(currentTime)) {
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			pw.println(
					"<h2 class='bg-danger text-light text-center'>Intime should be greater than or equal to current time</h2>");
			pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
			pw.println("</div>");
			pw.close();
			return;
		}
		if (LocalTime.parse(Outtime).isBefore(LocalTime.parse(Intime))) {
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			pw.println("<h2 class='bg-danger text-light text-center'>Outtime should be greater than intime</h2>");
			pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
			pw.println("</div>");
			pw.close();
			return;
		}

		boolean success = ServletUtils.insertData(Name, Purpose, Meetingperson, Gender, Mobile, City, Date, Intime,
				Outtime, Email);
		boolean succ = SoftdeleteServlet.insertData(Name, Purpose, Meetingperson, Gender, Mobile, City, Date, Intime,
				Outtime, Email);

		if (success & succ) {
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");

			pw.println(
					"<h2 class='bg-danger text-light text-center' style='margin-top: 100px;'>Registration sucess</h2>");

			pw.println("</div>");
		} else {
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			pw.println(
					"<h2 class='bg-danger text-light text-center'>Registration failed. Please try again later.</h2>");

			pw.println("</div>");
		}
		res.sendRedirect("reg.html");
		pw.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}
}
