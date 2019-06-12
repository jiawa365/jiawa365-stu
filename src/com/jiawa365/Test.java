
package com.jiawa365;

import java.util.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;//java.sql�������ݿ�

/**
 * jdbc��������
 * 
 * @author lenovo
 *
 */
public class Test {
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) throws Exception {

//		register();
		String str="����";
		String coded=URLEncoder.encode(str,"utf-8");
		
		System.out.println(URLDecoder.decode(coded,"utf-8"));
		
		

	}

	private static void showUser() throws SQLException {
		//��ʾ�û��б�
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		System.out.println("���\t����\t����");
		try {
			//1.��������
			Class.forName("com.mysql.jdbc.Driver");
			//2.��������
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			//3.����statement
			st=conn.createStatement();
			String sql="select * from t_user";
			//4.ִ��sql
			rs=st.executeQuery(sql);
			//ѭ����������
			while(rs.next()) {
				//��ȡָ���е�ֵ
				//�����±��ȡ���
				//int id = rs.getInt(1);
				//����������ȡ����
				int id = rs.getInt("id");
				
				
				String userName=rs.getString("user_name");
				String userPass=rs.getString("user_pass");
				//����������Ҫƴ���ַ���ʱ������ʹ��StringBuffer(�̰߳�ȫ),StringBuilder
				
				StringBuffer sb=new StringBuffer();
				sb.append(id+"\t");
				sb.append(userName+"\t");
				sb.append(userPass);
				System.out.println(sb.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.�ͷ���Դ
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
	 * ��¼
	 * @throws SQLException 
	 */
	public static void login() throws SQLException {
		System.out.println("��ӭ��¼��");
		System.out.println("�������û�����");
		String userName = scanner.nextLine();
		System.out.println("���������룺");
		String userPass = scanner.nextLine();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2.��������
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			String sql="SELECT * FROM t_user where user_name=? and user_pass=?";
			// 3.Ԥ�������,ֻҪ���û�����Ĳ������������Ԥ����������
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPass);
			// 4.ִ��sql
			 rs = ps.executeQuery();
			//�ж��Ƿ��������
			 if(rs.next()) {
				System.out.println("��¼�ɹ�"); 
			 }else {
				 System.out.println("�û������������");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.�ͷ�����
			
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
		// ע���û�
		System.out.println("��ӭע�᣺");
		System.out.println("�������û�����");
		String userName = scanner.next();
		System.out.println("���������룺");
		String userPass = scanner.next();

		// jdbc������������

		Connection conn = null;
		Statement st = null;

		try {
			// 1.����������
			Class.forName("com.mysql.jdbc.Driver");
			// 2.�������Ӷ���
			// ���ݿ������ַ���
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			// 3.����statement����
			st = conn.createStatement();
			String sql = "insert into t_user values(null,'" + userName + "','" + userPass + "')";
			// 4.ִ��sql
			st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.�ͷ���Դ
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
