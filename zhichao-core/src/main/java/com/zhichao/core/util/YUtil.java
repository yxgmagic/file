package com.zhichao.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用方法
 * 
 * @author imyzt
 * @since 2018-01-17
 */
public class YUtil {

	/**
	 * 判断字符串是否为空<br/>
	 * trim = true时去空格比较
	 * @param str 字符串
	 * @param trim 是否去空格
	 * @return
	 */
	public static boolean isNullOrEmpty(String str, boolean trim){

		str = trim && str != null ? str.trim() : str;

		boolean bool = "".equals(str) || str == null ? true : false;

		return bool;
	}

	/**
	 * 比较两个字符串是否相同
	 * @param a 字符串a
	 * @param b 字符串b
	 * @param trim 是否去空格
	 * @param ignoreCase 是否区分大小写
	 * @return
	 */
	public static boolean equals(String a, String b, boolean trim, boolean ignoreCase){

		boolean bool  = a == null && b == null ? true : false;

		if (a != null && b != null) {

			if (trim) {
				a = a.trim();
				b = b.trim();
			}

			if (ignoreCase) {
				bool = a.toLowerCase().equals(b.toLowerCase());
			}else{
				bool = a.equals(b);
			}
		}
		return bool;
	}

	/**
	 * 在字符串1的指定位置插入字符串2
	 * @param source 字符串1
	 * @param newString 字符串2
	 * @param startwith 插入位置
	 * @return
	 */
	public static String fillString(String source, String newString, int startwith){

		//判断是否为空
		if (isNullOrEmpty(source, false) || isNullOrEmpty(newString, false)) {
			throw new IllegalArgumentException("字符串不能为空或null");
		}

		//判断位置是否大于startwith
		if (startwith > source.length()) {
			throw new IllegalArgumentException("指定长度" + startwith + "超过source.length()");
		}

		StringBuffer sb = new StringBuffer(source);
		sb.insert(startwith, newString);

		return sb.toString();
	}

	/**
	 * 去除传入参数后面的 2 * X 个 0
	 * eg：
	 * 		430000，输出43
	 * 		430200，输出4302
	 * 
	 * @param str 传入参数
	 * @return
	 */
	public static String cutStr(String str) {
		str = str.trim();
		if ("00".equals(str.substring(str.length() - 2, str.length()))) {
			if (str.length() == 2) {
				return str;
			}else {
				return cutStr(str.substring(0, str.length() - 2));
			}
		}else {
			return str;
		}
	}

	/**
	 * 将Date转换成String格式的时间
	 * 
	 * @param date 时间
	 * @param format 时间格式(默认为yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String DateToString(Date date, String format) {

		format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;

		if (date != null) {

			SimpleDateFormat df = new SimpleDateFormat(format);

			return df.format(date);
		}

		return null;

	}

	
	/**
	 * 将String类型的时间转换成Date格式
	 * 
	 * @param date String时间
	 * @param format  转换格式（默认以yyyy-MM-dd HH:mm:ss解析）
	 * @return
	 */
	public static Date StringToDate(String... str) {

		if (str != null && str.length <= 2) {
			
			String format = str.length == 2 ? str[1] : "yyyy-MM-dd HH:mm:ss";
			
			SimpleDateFormat df = new SimpleDateFormat(format);

			try {
				return df.parse(str[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
