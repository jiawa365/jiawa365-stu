package com.jiawa365;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ת��ҵ����ʾ
 * @author lenovo
 *
 */
public class Demo1 {
	public static void main(String[] args) throws SQLException {
		

		Connection conn = null;
		Statement st = null;
		ResultSet rs=null;
		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2.��������
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			st = conn.createStatement();
			//������50
			String sql="update t_user set money=money-50 where user_name='����'";
			
			
			
			//ִ��ת��ҵ��ǰ�����뿪������
			conn.setAutoCommit(false);//�ֶ��ύ
			st.executeUpdate(sql);
			//�����˻���50
			 sql="update t_user set money=money+50 where user_name='����'";
			 
			 st.executeUpdate(sql);
			 
			 //ִ�гɹ����ύ����
			 conn.commit();
			 
		} catch (Exception e) {
			e.printStackTrace();
			//ת��ʧ�ܣ���ԭ����
			conn.rollback();
		} finally {
			// 5.�ͷ�����
			if (st != null) {
				st.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
		
	}

}
