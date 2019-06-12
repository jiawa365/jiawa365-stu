package com.jiawa365.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestCase {

	public static void main(String[] args) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
		Statement st = conn.createStatement();
		//st.execute("create table t_user (id int,name varchar(20))");
		st.executeUpdate("insert into t_user values (1,'уехЩ')");
		
		conn.close();

	}
}
