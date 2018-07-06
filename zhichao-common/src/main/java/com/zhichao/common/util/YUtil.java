package com.zhichao.common.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 常用方法
 *
 * @author imyzt
 * @since 2018-01-17
 */
public class YUtil<T> {

	private final static String ENCODE = "UTF-8";

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
	 * 判断字符串是否为空,如果不为空去首尾空格返回<br/>
	 * trim = true时去空格比较
	 * @param str 字符串
	 * @param trim 是否去空格
	 */
	public static String isNullOrEmptyReturnString(String str, boolean trim) {

		str = trim && str != null ? str.trim() : str;

		boolean bool = "".equals(str) || str == null ? true : false;

		if (!bool) {
			return str.trim();
		}
		return null;
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
		if (str == null){
			throw new RuntimeException("参数为Null");
		}
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

		format = isNullOrEmpty(format, true) ? "yyyy-MM-dd HH:mm:ss" : format;

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

		if (str[0] != null && str.length <= 2 && !"".equals(str[0])) {

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

	/**
	 * URL 解码
	 * @return String
	 */
	public static String URLDecoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLDecoder.decode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * URL 编码
	 *
	 * @return String
	 */
	public static String URLEncoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对Layer时间区间时间进行分割
	 * <p>
	 *     例如:2018-01-31 00:00:00 ~ 2018-02-15 00:00:00
	 * </p>
	 * <p>
	 *     分割成:
	 *     <li>
	 *         2018-01-31 00:00:00
	 *     </li>
	 *     <li>
	 *         2018-02-15 00:00:00
	 *     </li>
	 * </p>
	 * @param date 时间区间
	 * @param split 分隔符,默认为"~"
	 * @return
	 */
	public static String[] LayerDateTimeSplit(String date, String split){

		split = null == split ? "~" : split;

		if (!isNullOrEmpty(date, true)){
			String[] spl = date.split(split);

			return spl;
		}

		return null;
	}

	public static Map<String, Object> resultMap(int code, Object data, String msg){
		Map<String, Object> resultMap = new HashMap<>();
		msg = null == msg ? "操作成功!" : msg;
		resultMap.put("code",code);
		resultMap.put("msg",msg);
		resultMap.put("data", data);
		return resultMap;
	}

	/**
	 * 将[1,2,3,4,5](Object)数组转换为 1,2,3,4,5(String)
	 * @param obj 需要转换为String的数组
	 * @return
	 */
	public static String arrToStr(Object[] obj){
		if (obj == null){
			throw new RuntimeException("数组为空");
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : obj) {
			sb.append("," + o);
		}
		String result = sb.toString();
		return result.substring(1);
	}

	/**
	 * 将1,2,3,4,5(String) 转换为[1,2,3,4](Array)
	 * @param str 需要转化的String的数组
	 * @param split 分隔符
	 * @return
	 */
	public static String[] strToArr(String str, String split){
		if (StrUtil.isBlank(str)) {
			throw new RuntimeException("字符串为空");
		}
		if (StrUtil.isEmpty(split)) {
			throw new RuntimeException("分隔符为空");
		}
		return str.split(split);
	}

	/**
	 * 分割时间区间
	 * @param datetime 字符串时间
	 * @return
	 */
	public static String[] splitTimeInterval(String datetime){
		if (!YUtil.isNullOrEmpty(datetime, true)) {

			datetime = YUtil.isNullOrEmptyReturnString(datetime, true);

			String[] time = datetime.split("~");

			return time;
		}
		return null;
	}


	/**
	 * 获取jar形式运行时jar包的安装路径
	 * @return
	 */
	public static File getJarInstallPath(){

		try {
			File file = new File(ResourceUtils.getURL("classpath:").getPath());
			if (!file.exists()){
				file = new File("");
			}
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("获取jar包路径时出错.");
	}

	/**
	 * 返回文件流给前台
	 * @param file 文件
     * @param fileName 文件名称
	 * @return
	 */
	public static ResponseEntity<FileSystemResource> export(File file, String fileName) throws UnsupportedEncodingException {
		if (file == null || !file.exists()) {
			throw new RuntimeException("文件不存在");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Last-Modified", new Date().toString());
		headers.add("ETag", String.valueOf(System.currentTimeMillis()));
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new FileSystemResource(file));
	}
}
