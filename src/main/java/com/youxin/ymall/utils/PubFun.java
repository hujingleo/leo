package com.youxin.ymall.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.rsclouds.util.StringTool;

/**
 * 公用函数，静态调用
 * 
 * @category
 * 
 * 
 * 
 * 
 */
public class PubFun {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final DateFormat dateFormats = new SimpleDateFormat("yyyyMMdd");

	private static final DateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat timeformats = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

	public static final String SESSION_NAME = "session_object";

	public static final String SESSION_AUTH_NAME = "session_auth";

	public static String BASEPATH; // 应用系统的根目录

	/**
	 * 
	 * Description：将时间转为固定的字符串格式 @param @param date 毫秒形式 @param @return @return
	 * String @throws
	 */
	public static String parseDateToString(Date date) {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}

	/**
	 * 
	 * Description： 拿到流水号 @param @return @return String @throws
	 */
	public static String getFlowId() {
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999999 - 10000000 + 1)) + 10000000; // 获取随机数
		return (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()))) + rannum;
	}

	/**
	 * 
	 * Description：根据当前页，获取当前页左右边的页码，用于分页 如当前是第3页： （ 前一页 1 2 3 4 5 后一页） （ l1 p1
	 * p2 p3 p4 p5 r1）
	 * 
	 * @param currentPage
	 * @param numInPage
	 * @param total
	 * @return
	 *
	 */
	public static Map<String, Object> countCurrPageNearPages(int currentPage, int numInPage, int total,
			Map<String, Object> map) {
		Integer l1 = -1, r1 = -1;// 如果是-1就不显示了
		Integer pageData[] = new Integer[5];
		for (int i = 0; i < pageData.length; i++) {
			pageData[i] = -1;
		}

		int pageNum = 0;// 总页数
		if (total % numInPage == 0) {
			pageNum = total / numInPage;
		} else {
			pageNum = total / numInPage + 1;
		}
		if (currentPage > pageNum) {
			currentPage = pageNum;
		}
		// 分3种情况，小于5等于5和大于5
		if (pageNum <= 5)// 小于5
		{
			if (currentPage == 1) // 前一页
			{
				l1 = 1;
				r1 = 1;
			} else {
				l1 = currentPage - 1;
			}
			for (int i = 0; i < pageNum; i++) {
				pageData[i] = i + 1;
			}

			if (currentPage + 1 <= pageNum) {
				r1 = currentPage + 1;
			} else {
				r1 = currentPage;
			}
		} else// 大于5
		{
			if (currentPage - 1 < 1) {
				l1 = 1;
			} else {
				l1 = currentPage - 1;
			}
			int distanceP = currentPage - 1; // 当前页距离第一页相差多少页
			int distanceN = pageNum - currentPage; // 当前页距离最后一页相差多少页

			if (distanceP == 0) // 前面没有其他页
			{
				for (int i = 0; i < 5; i++) {
					pageData[i] = currentPage + i;
				}
			} else if (distanceP == 1)// 前面有一页
			{
				for (int i = -1; i < 4; i++) {
					pageData[i + 1] = currentPage + i;
				}
			} else if (distanceN >= 2) // 如果当前页后面还有2页以上
			{
				for (int i = -2; i < 3; i++) {
					pageData[i + 2] = currentPage + i;
				}
			} else if (distanceN == 1)// //如果当前页后面还有1页
			{
				for (int i = -3; i < 2; i++) {
					pageData[i + 3] = currentPage + i;
				}
			} else if (distanceN == 0)// //如果当前页后面还有0页
			{
				for (int i = -4; i < 1; i++) {
					pageData[i + 4] = currentPage + i;
				}
			}
			if (currentPage + 1 <= pageNum) {
				r1 = currentPage + 1;
			} else {
				r1 = currentPage;
			}

		}

		map.put("l1", l1);
		map.put("r1", r1);
		map.put("pageData", pageData);
		map.put("pageNum", pageNum);// 总页数
		map.put("numInPage", numInPage);// 每页数量
		map.put("currentPage", currentPage);// 当前页码
		map.put("total", total);// 总数
		return map;
	}

	/**
	 * 数据库分页使用
	 * 
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页多少条
	 * @param total
	 *            总数
	 * @return
	 */
	public static int countStartRow(Integer pageIndex, Integer pageSize, Integer total) {

		int startRow = 0;
		if (pageIndex > 0) // 全部
		{
			if (pageIndex < 0) {
				pageIndex = 1;
			}
			startRow = (pageIndex - 1) * pageSize; // 开始坐标

			// 判断startRow是否大于等于total，如果是就取最后一页坐标作为startRow的值
			if (startRow >= total && total > 0) {
				int i = total % pageSize;
				if (i == 0) {
					startRow = total - pageSize;
				} else {
					startRow = total - i;
				}
			}
		}
		return startRow;
	}

	/**
	 * 对指定字符串做MD5加密算法，返回加密后字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 小写转大写
	 */
	public static String getNumberToRMB(String m) {
		String num = "零壹贰叁肆伍陆柒捌玖";
		String dw = "圆拾佰仟万亿";
		String mm[] = null;
		mm = m.split(".");
		String money = mm[0];

		String result = num.charAt(Integer.parseInt("" + mm[1].charAt(0))) + "角"
				+ num.charAt(Integer.parseInt("" + mm[1].charAt(1))) + "分";

		for (int i = 0; i < money.length(); i++) {
			String str = "";
			int n = Integer.parseInt(money.substring(money.length() - i - 1, money.length() - i));
			str = str + num.charAt(n);
			if (i == 0) {
				str = str + dw.charAt(i);
			} else if ((i + 4) % 8 == 0) {
				str = str + dw.charAt(4);
			} else if (i % 8 == 0) {
				str = str + dw.charAt(5);
			} else {
				str = str + dw.charAt(i % 4);
			}
			result = str + result;
		}
		result = result.replaceAll("零([^亿万圆角分])", "零");
		result = result.replaceAll("亿零+万", "亿零");
		result = result.replaceAll("零+", "零");
		result = result.replaceAll("零([亿万圆])", "");
		result = result.replaceAll("壹拾", "拾");

		return result;
	}

	/**
	 * 将指定字符串转为UTF-8编码
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 取当前日期，返回固定格式字符串
	 */
	public static String getNow() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 取当前日期，返回Date形式
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 根据指定符号，拆分字符串 如果参数为空，返回空对象，解析异常异返回空对象
	 * 
	 * @param orgString
	 *            源字符串
	 * @param 指定符号
	 * @return 拆分后的数组
	 */
	public static String[] getStringArray(String orgString, String flag) {
		String[] returnValue = null;
		try {
			if (orgString != null && flag != null && !"".equals(orgString)) {
				returnValue = orgString.split(flag);
			}
		} catch (Exception e) {
		}
		return returnValue;
	}

	/**
	 * 根据指定符号，拆分字符串 如果参数为空，返回空对象，解析异常异返回空对象
	 * 
	 * @author 龙宏海 Dec 21, 2008
	 * @param orgString
	 *            源字符串
	 * @param 指定符号
	 * @return 拆分后的数组
	 */
	public static Long[] getLongArray(String orgString, String flag) {
		Long[] returnValue = null;
		try {
			if (orgString != null && flag != null && !"".equals(orgString)) {
				String values[] = orgString.split(flag);
				returnValue = new Long[values.length];
				for (int i = 0; i < values.length; i++) {
					returnValue[i] = Long.valueOf(values[i]);
				}
			}
		} catch (Exception e) {
		}
		return returnValue;
	}

	/**
	 * 生成8位序列号,加上前缀总共10位,不足补0
	 * 
	 * @author 彭振民
	 * @param prefix
	 * @param seqNumber
	 *            序列号
	 * @return
	 */
	public static String generateCode(String prefix, String seqNumber) {
		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		int len = seqNumber.length();
		int addZeorLen = 8 - len;
		if (addZeorLen < 0) {
			addZeorLen = 0;
		}

		for (int i = 0; i < addZeorLen; i++) {
			sb.append("0");
		}
		sb.append(seqNumber);
		return sb.toString();
	}

	/**
	 * 由指定格式转换字符串为时间格式，如果转换异常，则返回空对象
	 * 
	 * @param sDate
	 *            时间字符串格式
	 * @param format
	 *            格式format
	 * @return
	 */
	public static Date stringToDate(String sDate, String format) {
		Date date = null;
		if (sDate != null && format != null && !"".equals(sDate)) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(sDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;

	}

	/**
	 * 方法名称：dateToString 内容摘要：将一个日期转换成字符串，如：2008-08-18
	 * 
	 * @param Date
	 *            date 需要转换的日期
	 * @return String 返回一个字符串类型
	 */
	public static String dateToString(Date date) {
		if (date != null) {
			return dateFormat.format(date);
		} else {
			return "";
		}
	}

	public static String dateToStrings(Date date) {
		if (date != null) {
			return dateFormats.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return timeformat.format(java.util.Calendar.getInstance().getTime());
	}

	/**
	 * 功能:把对象中属性值为空子串的转为null
	 * 
	 * @param obj
	 *            要转换的对象
	 * @return
	 */
	public static Object ConvertEmptyValueToNull(Object obj) {
		Class c = obj.getClass();
		try {
			Field[] fs = c.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].getType().toString().equals(String.class.toString())) {
					String name = fs[i].getName();// 得到当前类中的字段名称,因为只有公有的才能访问
					String getname = "get" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);// 组合成get方法
					String setname = "set" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);// 组合成set方法
					Method getMethod = c.getDeclaredMethod(getname);// 得到该类中所有定义的方法
					String str = (String) getMethod.invoke(obj);// 取出方法中的返回值，也就是STRUTS把前台的入参放入BEAN中的值
					if (str != null && str.length() == 0) {
						Method setMethod = c.getDeclaredMethod(setname, String.class);// 得到set方法的实例
						setMethod.invoke(obj, new Object[] { null });// 设置set方法的值为null
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return obj;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String nullToString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	/**
	 * 获区HIBERNATE的查询属性,方便多个查询,无需视图 如:select a.userName,b.sid,c.dd from InfoUser
	 * a,InfoDept b,sh c 输出为:[userName,sid,dd]
	 * 
	 * @param sql
	 * @return
	 */
	public static String[] getMetaData(String sql) {
		String[] source = sql.split("(?i)from")[0].split("select")[1].split(",");
		String[] dest = new String[source.length];
		for (int i = 0; i < source.length; i++) {
			String[] tmp = source[i].trim().split("\\.")[source[i].trim().split("\\.").length - 1].split(" ");
			dest[i] = tmp[tmp.length - 1];
		}
		return dest;
	}

	public static String encodeStrToUtf8(String orgStr) {
		String result = null;
		try {
			result = new String(orgStr.getBytes("GB2312"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orgStr;
	}

	public static String parseList(List<String> list, String reg) {
		String result = null;
		if (list != null) {
			String temp;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				temp = list.get(i);
				if (sb.length() > 0) {
					sb.append(reg);
					sb.append(temp);
				} else {
					sb.append(temp);
				}
			}
			result = sb.toString();
		}
		return result;
	}

	/**
	 * 按规则获取编号
	 * 
	 * @param seqStr
	 * @return
	 */
	public static String getCodeInfoStr(String headStr, int totalLen, String existCode) {
		String reItemCode = ""; // 返回的项目编号
		String tempItemStr = "";
		int initNum = 1;
		System.out.println("totalLen : " + totalLen);
		// 编号不存在
		if (null == existCode || "".equals(existCode)) {
			for (int j = 0; j < totalLen - 1; j++) {
				tempItemStr = tempItemStr + "0";
			}
			reItemCode = headStr + tempItemStr + initNum;
		}
		// 编号存在
		else {
			String tempStr = "";
			Integer tempInt;
			int headStrLen;
			headStrLen = headStr.length();
			System.out.println("headStrLen : " + headStrLen);
			System.out.println("existCode.length() : " + existCode.length());
			tempStr = existCode.substring(headStrLen, existCode.length());
			System.out.println("tempStr : " + tempStr);
			tempInt = Integer.valueOf(tempStr);
			++tempInt;
			int tempIntLen = (tempInt + "").length();
			int addLen = totalLen - tempIntLen;
			// 将编号前面加"0"填充至总长度
			if (addLen > 0) {
				for (int j = 0; j < addLen; j++) {
					tempItemStr = tempItemStr + "0";
				}
				reItemCode = headStr + tempItemStr + tempInt;
			} else {
				reItemCode = headStr + tempInt;
			}
		}
		System.out.println("reItemCode : " + reItemCode);

		return reItemCode;
	}

	/**
	 * 校验值是否为数字
	 * 
	 * @param string
	 * @return
	 */
	public static String checkValueIsNum(String string, String msg) {
		String message = "";
		if (null != string && !string.equals("") && !string.matches("[0-9]*")) {
			message = "\"" + msg + "\"只能输入数字";
		} else {
			System.out.println("string.matches: " + string.matches("[0-9]*"));
		}
		return message;
	}

	/**
	 * 校验值是否为日期
	 * 
	 * @param string
	 * @return
	 */
	public static String checkValueIsDate(String string, String msg) {
		String message = "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null != string && !"".equals(string)) {
			try {
				Date date = format.parse(string);
				System.out.println("checkValueIsDate-date : " + date);
			} catch (ParseException e) {
				message = "\"" + string + "\"不符合日期格式\"yyyy-MM-dd\",请重新输入";
				e.printStackTrace();
			}
		}
		return message;
	}

	/**
	 * 浏览器名称
	 * 
	 * @param userbrowser
	 * @return
	 */
	public static String getBrowser(String userbrowser) {
		String browsername = "";
		if (userbrowser.toLowerCase().indexOf("qq") > 0) {
			browsername = "QQ浏览器";
		} else if (userbrowser.toLowerCase().indexOf("chrome") > 0 && userbrowser.toLowerCase().indexOf("safari") > 0) {
			browsername = "Google Chrome";
		} else if (userbrowser.toLowerCase().indexOf("safari") > 0
				&& userbrowser.toLowerCase().indexOf("chrome") <= 0) {
			browsername = "Safari";
		} else if (userbrowser.toLowerCase().indexOf("ie") > 0 && userbrowser.toLowerCase().indexOf("360se") > 0) {
			browsername = "360浏览器";
		} else if (userbrowser.toLowerCase().indexOf("firefox") > 0) {
			browsername = "Firefox";
		} else if (userbrowser.toLowerCase().indexOf("ie") > 0) {
			browsername = "IE";
		} else {
			browsername = "other";
		}
		return browsername;
	}

	/**
	 * 根据javabean对象更新对应的数据库表
	 * 
	 * @param u
	 *            ：javabean对象
	 * @param table
	 *            ：数据库表名
	 * @param keyword
	 *            :关键字，即sql语句中where部分的关键字
	 * @return
	 * @throws Exception
	 */
	public static <T> String getUpdateSql(T u, String table, String keyword) throws Exception {
		String sql = "update " + table + " set ";
		Field[] cts = u.getClass().getDeclaredFields();
		for (Field f : cts) {
			if (!f.getName().equals("serialVersionUID") && !f.getName().equalsIgnoreCase("id")
					&& !getValue(u, f.getName()).equals("")) {
				sql += f.getName() + "='" + getValue(u, f.getName()) + "',";
			}
		}
		sql = sql.substring(0, sql.length() - 1) + " where " + keyword + " = '" + getValue(u, keyword) + "'";
		System.out.println(sql);
		return sql;
	}

	/**
	 * 根据javabean对象插入到数据库表中
	 * 
	 * @param u
	 *            ：javabean对象
	 * @param table
	 *            ：数据库表名
	 * @return
	 * @throws Exception
	 */
	public static <T> String getInsertSql(T u, String table) throws Exception {
		String sql = "insert into " + table + "(";
		Field[] cts = u.getClass().getDeclaredFields();
		for (Field f : cts) {
			if (!f.getName().equals("serialVersionUID") && !f.getName().equalsIgnoreCase("id")
					&& !getValue(u, f.getName()).equals("")) {
				sql += f.getName() + ",";
			}
		}
		sql = sql.substring(0, sql.length() - 1) + ") values ( ";
		for (Field f : cts) {
			if (!f.getName().equals("serialVersionUID") && !f.getName().equalsIgnoreCase("id")
					&& !getValue(u, f.getName()).equals("")) {
				if ("geom".equals(f.getName())) {
					sql += getValue(u, f.getName()) + ",";
				} else {
					sql += "'" + getValue(u, f.getName()) + "',";
				}
			}
		}
		sql = sql.substring(0, sql.length() - 1) + ")";
		return sql;
	}

	/**
	 * 
	 * @param u
	 *            为用户传入的javabean
	 * @param s
	 *            为属性的名字
	 * @return
	 * @throws Exception
	 */
	private static <T> String getValue(T u, String s) throws Exception {
		s = "get" + s.replaceFirst(s.charAt(0) + "", (s.charAt(0) + "").toUpperCase());
		Method m = u.getClass().getDeclaredMethod(s);
		Object o = m.invoke(u);
		return o == null ? "" : o.toString();
	}

	/**
	 * 根据输入的表名、字段名和值获取sql语句
	 * 
	 * @param input
	 * @param table
	 * @return
	 */
	public static String getSqlByParameters(Map<String, String> input, String table) {
		if (input.isEmpty()) {
			return null;
		}
		String tempKey = "", tempValue = "";
		String sql = "select * from " + table + " where ";
		Iterator<Entry<String, String>> iterator = input.entrySet().iterator();
		for (int i = 0; i < input.size(); i++) {
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			tempKey = entry.getKey().toString();
			tempValue = entry.getValue().toString();
			if (i > 0) {
				sql += " and " + tempKey + " = '" + tempValue + "'";
			} else {
				sql += tempKey + " = '" + tempValue + "'";
			}
		}
		return sql;
	}

	/**
	 * 根据输入的表名、字段名和值获取插入类型的sql语句和参数数组
	 * 
	 * @param input
	 * @param table
	 * @return map.sql = sql语句 ； map.args = 参数数组
	 */
	public static Map getSqlByParams4insert(Map<String, String> input, String table) {
		if (input.isEmpty()) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String tempKey = "", tempValue = "";
		String sql = "insert into " + table + " (";
		String sql2 = " values (";
		Object[] args = new Object[] {};
		Iterator<Entry<String, String>> iterator = input.entrySet().iterator();
		for (int i = 0; i < input.size(); i++) {
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			tempKey = entry.getKey().toString();
			tempValue = entry.getValue().toString();
			if (i > 0) {
				sql += " ," + tempKey;
				sql2 += " ,?";
			} else {
				sql += tempKey;
				sql2 += " ?";
			}
			args[i] = tempValue;
		}
		sql += ")";
		sql2 += ")";
		map.put("sql", sql + sql2);
		map.put("args", args);
		return map;
	}

	public static String excuteCommand(String command) {
		Runtime r = Runtime.getRuntime();
		String inline = "";
		Process p;
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((inline = br.readLine()) != null) {
				System.out.println(inline);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inline;
	}

	/**
	 * 用来生成virtualbox中vrdp认证中所需要的hash密码
	 * 
	 * @param password
	 * @return
	 */
	public static String generatePasswordHash(ResourceBundle rb, String password) {
		String command = "vboxmanage internalcommands passwordhash " + password;
		String line = "", hash = "";
		// 下面这个是针对virtualbox部署在window下的
		// line =
		// "Password hash:
		// 0ed44b34f7a2c2d3e6281300f358789702949d84293c7bf5d5388f5495303efd";
		try {
			// line =
			// linuxRemoteCommand(rb.getString("virtualbox_host_inner_ip"),
			// rb.getString("virtualbox_host_account"),
			// rb.getString("virtualbox_host_password"), command);
			// 在北京临时演示用
			line = "Password hash: 0ed44b34f7a2c2d3e6281300f358789702949d84293c7bf5d5388f5495303efd";
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("打印的hash密码为：" + line);
		hash = line.substring(line.indexOf(":") + 2);
		return hash;
	}

	/**
	 * 获取客户端的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains("127.0.0.1") || ip.contains("localhost") || ip.contains("192.168")
				|| ip.contains("0:0:0:0:0:0:0:1")) {// ip为0:0:0:0:0:0:0:1时，指localhost
			// 将公司的内网地址转为公网地址，以方便获取对应的地理信息
			ip = "183.63.212.125";
		}
		return ip;
	}

	// public static void main(String[] args) throws JSONException{
	// System.out.println(PubFun.getAreaFromIp("183.63.212.125"));
	// }

	/* 编码：对字符串进行编码码操作 */
	public static String encode4Url(String target) {
		String dataname = "";
		try {
			dataname = java.net.URLEncoder.encode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dataname;
	}

	/**
	 * 
	 * Description：解码：对js前台传输过来的字符串进行解码操作
	 * 
	 * @param @param
	 *            target 要转换的字符串 @return String @throws
	 */
	public static String decode(String target) {
		String dataname = "";
		try {
			dataname = java.net.URLDecoder.decode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dataname;
	}

	/**
	 * 构建目录
	 * 
	 * @param outputDir
	 * @param subDir
	 */
	public static void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			file.mkdirs();
		}

	}

	/**
	 * 从一份txt文件中读取字符串
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readStrFromTxt(String filePath) {
		StringBuffer sb = new StringBuffer();
		File f = new File(filePath);
		if (f.isFile() && f.exists()) {
			InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				try {
					while ((lineTXT = bufferedReader.readLine()) != null) {
						sb.append(lineTXT.toString().trim());
					}
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("找不到指定的TXT文件！");
			return null;
		}
		return sb.toString();
	}

	/**
	 * 特殊符号转义
	 */
	public static String TransferSpecificSymbolMeaning(String m) {
		if (m != null && !m.equals("") && (m.indexOf("<") >= 0) || (m.indexOf(">") >= 0)) {
			m = m.replace("<", "&lt");
			m = m.replace(">", "&gt");
		}
		return m;
	}

	/**
	 * 判断是否符合sql注入规则，符合返回空值
	 * 
	 * @param m
	 * @return
	 */
	public static Boolean checkSQLInjection(String m) {
		boolean flag = false;
		String CHECKSQL1 = "/(\\%27)|(\\’)|(\\-\\-)|(\\%23)|(#)/ix";// 检测SQL
																	// meta-characters的正则表达式
		String CHECKSQL2 = "/((\\%3D)|(=))[^ ]*((\\%27)|(\\’)|(\\-\\-)|(\\%3B)|(:))/i";// 修正检测SQL
																						// meta-characters的正则表达式
		String CHECKSQL3 = "/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";// 典型的
																							// SQL
																							// 注入攻击的正则表达式
		String CHECKSQL4 = "/((\\%27)|(\\’))union/ix(\\%27)|(\\’)";// 检测SQL注入，UNION查询关键字的正则表达式
		String CHECKSQL5 = "/exec(\\s|\\+)+(s|x)p\\w+/ix";// 检测MS SQL Server
															// SQL注入攻击的正则表达式
		// String CHECKSQL6 =
		// "'|and|exec|insert|select|delete|update|
		// count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";//字符串过滤
		// String[] inj_stra=CHECKSQL6.split("\\|");
		//
		// for (int i=0 ; i < inj_stra.length ; i++ ){
		// if (m.indexOf(inj_stra[i])>=0){
		// return " ";
		// }
		// }
		// return m;
		// }

		if (Pattern.matches(CHECKSQL1, m))
			flag = true;
		if (Pattern.matches(CHECKSQL2, m))
			flag = true;
		if (Pattern.matches(CHECKSQL3, m))
			flag = true;
		if (Pattern.matches(CHECKSQL4, m))
			flag = true;
		if (Pattern.matches(CHECKSQL5, m))
			flag = true;
		return flag;
	}

	/**
	 * 替换.;-为空格字符
	 * 
	 * @param str
	 * @return
	 */
	public static String TransactSQLInjection(String str) {
		return str.replaceAll(".*([';]+|(--)+).*", " ");
	}

	/**
	 * 
	 * Description：判断是否符合邮件格式
	 * 
	 * @param @param
	 *            email 邮件
	 * @param @return
	 * @return boolean true 为符合
	 * 
	 */
	public static boolean isEmail(String email) {
		boolean retval = false;
		String emailPattern = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+[.]([a-zA-Z0-9_-])+";
		retval = email.matches(emailPattern);
		String msg = "NO MATCH: pattern:" + email + "              regex: " + emailPattern;

		if (retval) {
			msg = "MATCH   : pattern:" + email + "              regex: " + emailPattern;
		}

		// System.out.println(msg + " ");
		return retval;
	}

	/*
	 * 移动: 2G号段(GSM网络)有139,138,137,136,135,134,159,158,152,151,150,
	 * 3G号段(TD-SCDMA网络)有157,182,183,188,187 147是移动TD上网卡专用号段. 联通:
	 * 2G号段(GSM网络)有130,131,132,155,156 3G号段(WCDMA网络)有186,185 电信:
	 * 2G号段(CDMA网络)有133,153 3G号段(CDMA网络)有189,180
	 */
	static String YD = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[2378]{1})|([4]{1}[7]{1}))[0-9]{8}$";
	static String LT = "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[56]{1})|([8]{1}[56]{1}))[0-9]{8}$";
	static String DX = "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[019]{1}))[0-9]{8}$";

	/**
	 * 
	 * Description：判断是否符合手机格式
	 * 
	 * @param @param
	 *            phone 手机
	 * @param @return
	 * @return boolean true 为符合
	 * 
	 */
	public static boolean isPhone(String phone) {
		boolean retval = false;
		// String YD =
		// "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[2378]{1})|([4]{1}[7]{1}))[0-9]{8}$";
		// String LT =
		// "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[56]{1})|([8]{1}[56]{1}))[0-9]{8}$";
		// String DX =
		// "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[019]{1}))[0-9]{8}$";
		//
		int flag;// 存储匹配结果
		// 判断手机号码是否是11位
		if (phone.length() == 11) {
			// 判断手机号码是否符合中国移动的号码规则
			if (phone.matches(YD)) {
				retval = true;
			}
			// 判断手机号码是否符合中国联通的号码规则
			else if (phone.matches(LT)) {
				retval = true;
			}
			// 判断手机号码是否符合中国电信的号码规则
			else if (phone.matches(DX)) {
				retval = true;
			}
			// 都不合适 未知
			else {
				retval = false;
			}
		}
		// 不是11位
		else {
			retval = false;
		}
		return retval;
	}

	/**
	 * 根据字符串获取手机号码，不规则的字符串，不确定的字符串
	 * 
	 * @param mobile
	 * @return
	 */
	public static String getMobileByString(String mobile) {
		try {
			if (StringTools.nil(mobile)) {
				return "";
			}
			// 先判断字符串是不是数字
			if (isNumeric(mobile)) {
				// 如果是11位的数字就是电话号码了
				if (mobile.length() == 11) {
					// 直接是电话号码返回
					return mobile;
				}
			}

			String[] ms = mobile.split("");
			// 分解不规则的字符串进行手机号码的提取,电话号码都是1开头的，那么就获取1开头的后面11位如果都是数字的话，那么就是他了
			for (int i = 1; i < ms.length; i++) {
				if (ms[i].equals("1"))// 如果是1开头就拿到后面的11位，如果11位是数字就是手机了
				{
					// 如果i的后面还有11位。。
					if (i + 10 <= ms.length) {
						String str = mobile.substring(i - 1, i + 10);
						if (isNumeric(str)) {
							return str;
						}
					}

				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	// public static void main(String[] args) {
	// System.out.println(PubFun.isPhone("13712398220"));
	// }

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// private static String spSymbool = "/[&\|\\\*^%$#@\-]/"

	public static boolean isHasSpSymbool(String str) {
		Pattern pattern = Pattern.compile("/[&\\|\\\\\\*^%$#@\\-]/");
		Matcher isHasSpSymbool = pattern.matcher(str);
		if (!isHasSpSymbool.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 获得随机的短信注册码
	 * 
	 * @return
	 */
	public static String getMsgCode() {
		Random random = new Random();

		return StringTool.padRight(String.valueOf(random.nextInt(1000000)), 6, '0');
	}

	/**
	 * 获取订单编号
	 * 
	 * @return
	 */
	public static String createOrderCode() {
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x + 100000;
		String result = DateTools.timeToDateFormat(new Date().getTime(), "yyyyMMddHHmmsssss");
		return result + x;
	}

	public static void main(String[] args) {
		// createOrderCode();
		System.out.println(getMobileByString("0769-23077003"));
	}

}
