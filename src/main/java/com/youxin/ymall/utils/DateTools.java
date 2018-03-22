package com.youxin.ymall.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTools {
	/**
	 * 日期转换：长时间（毫秒）——>指定日期格式（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param time
	 *            -相对于“1970 年 1 月 1 日，00:00:00 GMT”的毫秒数, pattern-模式匹配字符串
	 * @return
	 */
	public static String timeToDateFormat(long time, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
		sdf.applyPattern(pattern);// 设置日期显示格式
		Date date0 = new Date();
		date0.setTime(time);
		String dateStr1 = sdf.format(date0);
		return dateStr1;
	}

	/**
	 * 判断2个时间相差1个小时以上就返回false,1小时内就返回true
	 * 
	 * @param pBeginTime
	 *            开始
	 * @param pEndTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean timeCompareIsOneHour(String pBeginTime, String pEndTime) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Long beginL = format.parse(pBeginTime).getTime();
			Long endL = format.parse(pEndTime).getTime();

			if (3600000 < (endL - beginL)) {
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @param marg
	 *            转换格式如yyyy-MM-dd HH:mm:ss
	 * @return date
	 */
	public static Date StrToDate(String str, String marg) {

		SimpleDateFormat format = new SimpleDateFormat(marg);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * Description：为CloudOS API请求设置时间参数
	 * 
	 * @return
	 *
	 */
	public static String setTimeParamForOSApi() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 100);// 有效期设置10分钟
		Date expiredDate = cal.getTime();
		return dateFormat.format(expiredDate);
	}

	/**
	 * 
	 * Description：获取当前时间，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 *
	 */
	public static String nowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// 设置日期显示格式
		Date date0 = new Date();
		date0.setTime(System.currentTimeMillis());
		String dateStr1 = sdf.format(date0);
		return dateStr1;
	}

	/**
	 * 
	 * Description：时间的加法
	 * 
	 * @param time
	 * @return
	 *
	 */
	public static Date timePlus(Date date, int time) {
		Calendar afterTime = Calendar.getInstance();
		afterTime.setTime(date);
		afterTime.add(Calendar.MINUTE, time);
		Date afterDate = (Date) afterTime.getTime();
		return afterDate;
	}

	public static void main(String[] args) {
		System.out.println(timeCompareIsOneHour("2013-11-27 16:24:43", "2013-11-27 18:24:43"));
	}
}
