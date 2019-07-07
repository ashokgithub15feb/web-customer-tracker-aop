package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDBConnServlet
 */
@WebServlet("/TestDBConnServlet")
public class TestDBConnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection variable
		String user = "springstudent";
		String password = "springstudent";
		String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSl=false";
		String driver = "com.mysql.jdbc.Driver";
		// get the connetion
		Connection myCon = null;
		try {
			PrintWriter out = response.getWriter();

			out.println("Connection to database: " + url);

			Class.forName(driver);

			myCon = DriverManager.getConnection(url, user, password);

			out.println("\nConnection Successful!!!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		} finally {
			try {
				myCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
	}

}
