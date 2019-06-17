package com.jiawa365.util;

import java.util.regex.Pattern;

/**
 * 
   * 常用的正则表达式
 * 
 * @author 
 * <h1><a href="http://www.jiawa365.com">嘉哇科技</a><br>讲师：谭兴义</h1>
 * @time 2019年6月5日 下午2:21:57
 */
public class Regulars {

	/**手机号*/
	public static final String PHONE="^1[3-9]\\d{9}$";
	
	/**邮箱*/
	public static final String EMAIL="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	/**身份证*/
	public static final String ID_NUM="^\\d{15}|\\d{18}|\\d{17}X$";
	
	/**昵称*/
	public static String NICKNAME="^[\\u4e00-\\u9fa5_A-z0-9]{6,20}$";
	
}

