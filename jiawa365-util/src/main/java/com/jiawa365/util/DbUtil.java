package com.jiawa365.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 简化jdbc
 * 
 * @author
 *         <h1><a href="http://www.jiawa365.com">嘉哇科技</a><br>
 *         讲师：谭兴义</h1>
 * @time 2019年5月30日 下午1:35:43
 */
public class DbUtil {

	static String username;
	static String url;
	static String userpass;
	static String driverClass;

	static {
		// 1.读取properties文件内容
		Properties properties = new Properties();
		try {
			// 加载配置文件
			// 从类路径下读取文件
			InputStream is = DbUtil.class.getResourceAsStream("/db.properties");
			properties.load(is);
			is.close();
			username = properties.get("username").toString();
			url = properties.getProperty("url");
			userpass = properties.getProperty("userpass");
			driverClass = properties.getProperty("driverClass");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 查询单条数据
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> queryOne(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> data = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);
			// 预处理参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			rs = ps.executeQuery();

			// 获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列的数量
			int columnCount = metaData.getColumnCount();

			if (rs.next()) {
				data = new HashMap<String, Object>();
				// 将当前行的所有列存入map

				for (int i = 1; i <= columnCount; i++) {
					// 获取列的名称
					String columnName = metaData.getColumnLabel(i);
					data.put(columnName, rs.getObject(columnName));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
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
		return data;
	}

	/**
	 * 查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryOne(String sql, Class<T> clazz, Object... params) {
		T result = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);
			// 预处理参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			rs = ps.executeQuery();

			// 获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列的数量
			int columnCount = metaData.getColumnCount();

			// 获取所有的属性名
			List<String> filedNames = new ArrayList<String>();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				filedNames.add(fields[i].getName());
			}

			if (rs.next()) {
				// 调用类的无参（默认）构造器创建
				result = clazz.newInstance();

				// 将当前行的所有列存入map
				for (int i = 1; i <= columnCount; i++) {
					// 获取列的名称
					String columnName = metaData.getColumnLabel(i);

					for (String filedName : filedNames) {

						// 将下划线转驼峰
						String columnName1 = convert(columnName);

						if (filedName.equals(columnName) || filedName.equals(columnName1)) {
							// 属性名与列的名字相同，给属性赋值
							Field field = clazz.getDeclaredField(filedName);
							//临时修改权限
							field.setAccessible(true);
							// 赋值,第一个参数是对象，第二个参数是对象属性值
							field.set(result, rs.getObject(columnName));
							break;
						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
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
		return result;
	}

	/**
	 * 将下划线转驼峰
	 * 
	 * @param columnName
	 * @return
	 */
	private static String convert(String columnName) {
		String result = "";
		int i = columnName.indexOf("_");
		if (i > -1) {
			// 存在下划线，则转驼峰
			String left = columnName.substring(0, i); // 取出下划线左边的字符
			String right = columnName.substring(i + 2, columnName.length());// 下划线右侧字符
			String str = columnName.substring(i + 1, i + 2).toUpperCase();// 将下划线后的第一个字母转大写
			result = left + str + right;
		}

		// 判断是否还存在下划线
		if (result.indexOf("_") > -1) {
			result = convert(result);
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(convert("user_name_and_age"));
	}

	/**
	 * 查询多条数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> query(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);
			// 预处理参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			rs = ps.executeQuery();

			// 获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列的数量
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 将当前行的所有列存入map

				for (int i = 1; i <= columnCount; i++) {
					// 获取列的名称
					String columnName = metaData.getColumnLabel(i);
					map.put(columnName, rs.getObject(columnName));
				}

				datas.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
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
		return datas;
	}

	/**
	 * 查询多条数据
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> List<T> query(String sql, Class<T> clazz, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> datas = new ArrayList<T>();
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);
			// 预处理参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			rs = ps.executeQuery();

			// 获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列的数量
			int columnCount = metaData.getColumnCount();
			
			List<String> filedNames = new ArrayList<String>();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				filedNames.add(fields[i].getName());
			}

			while (rs.next()) {

				T obj = clazz.newInstance();

				// 将当前行的所有列存入map

				// 将当前行的所有列存入map
				for (int i = 1; i <= columnCount; i++) {
					// 获取列的名称
					String columnName = metaData.getColumnLabel(i);

					for (String filedName : filedNames) {

						// 将下划线转驼峰
						String columnName1 = convert(columnName);

						if (filedName.equals(columnName) || filedName.equals(columnName1)) {
							// 属性名与列的名字相同，给属性赋值
							Field field = clazz.getDeclaredField(filedName);
							// 赋值,第一个参数是对象，第二个参数是对象属性值
							//临时修改权限
							field.setAccessible(true);
							field.set(obj, rs.getObject(columnName));
							break;
						}

					}

				}
				datas.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
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
		return datas;
	}

	/**
	 * 统计数量
	 * 
	 * @param sql
	 * @return 匹配数据
	 */
	public static int count(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);

			// 设置预处理参数

			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
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

		return 0;

	}

	/**
	 * 通用的增删改的方法
	 * 
	 * @param sql 执行的sql语句
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object... params) {

		// 1.加载驱动

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, userpass);
			ps = conn.prepareStatement(sql);

			// 设置预处理参数

			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
