
package com.jiawa365;

import java.util.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;//java.sql操作数据库

/**
 * jdbc操作数据
 * 
 * @author lenovo
 *
 */
public class Test {
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) throws Exception {

//		register();
		String str="张三";
		String coded=URLEncoder.encode(str,"utf-8");
		
		System.out.println(URLDecoder.decode(coded,"utf-8"));
		
		

	}

	private static void showUser() throws SQLException {
		//显示用户列表
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		System.out.println("编号\t姓名\t密码");
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.创建连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//3.创建statement
			st=conn.createStatement();
			String sql="select * from t_user";
			//4.执行sql
			rs=st.executeQuery(sql);
			//循环遍历数据
			while(rs.next()) {
				//获取指定列的值
				//根据下标获取编号
				//int id = rs.getInt(1);
				//根据列名获取数据
				int id = rs.getInt("id");
				
				
				String userName=rs.getString("user_name");
				String userPass=rs.getString("user_pass");
				//当代码中需要拼接字符串时，建议使用StringBuffer(线程安全),StringBuilder
				
				StringBuffer sb=new StringBuffer();
				sb.append(id+"\t");
				sb.append(userName+"\t");
				sb.append(userPass);
				System.out.println(sb.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源
			if(rs!=null) {
				rs.close();
			}
			
			if(st!=null) {
				st.close();
			}
			
			if(conn!=null) {
				conn.close();
			}
			
		}
	}
	
	/**
	 * 登录
	 * @throws SQLException 
	 */
	public static void login() throws SQLException {
		System.out.println("欢迎登录：");
		System.out.println("请输入用户名：");
		String userName = scanner.nextLine();
		System.out.println("请输入密码：");
		String userPass = scanner.nextLine();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.创建连接
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			String sql="SELECT * FROM t_user where user_name=? and user_pass=?";
			// 3.预处理参数,只要是用户输入的参数，必须进行预处理！！！！
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPass);
			// 4.执行sql
			 rs = ps.executeQuery();
			//判断是否存在数据
			 if(rs.next()) {
				System.out.println("登录成功"); 
			 }else {
				 System.out.println("用户名或密码错误");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放连接
			
			if(rs!=null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void register() {
		// 注册用户
		System.out.println("欢迎注册：");
		System.out.println("请输入用户名：");
		String userName = scanner.next();
		System.out.println("请输入密码：");
		String userPass = scanner.next();

		// jdbc开发基本步骤

		Connection conn = null;
		Statement st = null;

		try {
			// 1.加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			// 2.创建连接对象
			// 数据库连接字符串
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			// 3.创建statement对象
			st = conn.createStatement();
			String sql = "insert into t_user values(null,'" + userName + "','" + userPass + "')";
			// 4.执行sql
			st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
