package com.jiawa365;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 转账业务演示
 * @author lenovo
 *
 */
public class Demo1 {
	public static void main(String[] args) throws SQLException {
		

		Connection conn = null;
		Statement st = null;
		ResultSet rs=null;
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.创建连接
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			st = conn.createStatement();
			//张三扣50
			String sql="update t_user set money=money-50 where user_name='张三'";
			
			
			
			//执行转账业务前，必须开启事务
			conn.setAutoCommit(false);//手动提交
			st.executeUpdate(sql);
			//李四账户加50
			 sql="update t_user set money=money+50 where user_name='李四'";
			 
			 st.executeUpdate(sql);
			 
			 //执行成功，提交事务
			 conn.commit();
			 
		} catch (Exception e) {
			e.printStackTrace();
			//转账失败，还原事务
			conn.rollback();
		} finally {
			// 5.释放连接
			if (st != null) {
				st.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
		
	}

}
