package com.favery.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class MyConnection {

	private static MyConnection myCon = null;

	private static final String URL = "jdbc:mysql://localhost:3305/favery";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private Connection con = null;

	private MyConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static MyConnection getInstance() {
		if (myCon == null) {
			myCon = new MyConnection();
		}
		return myCon;
	}

	public Connection getConnection() {
		return this.con;
	}
}
