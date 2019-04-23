package com.kingtopinfo.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Package com.kingtopinfo.base.util
 * @Description: TODO
 * @author cyf
 * @date 2017年10月25日 上午10:51:10
 */
public class TimeUtils {
	
	public static final String				DATE_FORMAT1		= "yyyy-MM-dd";
	private static SimpleDateFormat			datePattern			= new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat			datePatternYear		= new SimpleDateFormat("yyyy");
	private static SimpleDateFormat			dateTimePattern		= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat			dfyyyyMMdd			= new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat	dateTimePattern2	= new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat	dateTimePattern3	= new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat			SHORT_DATE_FORMAT	= new SimpleDateFormat(DATE_FORMAT1);
	public static final int YEAR = Calendar.YEAR;
	public static final int MONTH = Calendar.MONTH;
	public static final int DAY = Calendar.DATE;
	public static final int HOUR = Calendar.HOUR;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 生成20170911格式时间
	 * @author cyf
	 * @date 2017年9月11日 上午9:25:10
	 */
	public static String formatyyyyMMdd(Date date) {
		return dfyyyyMMdd.format(date);
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 生成201709110527格式时间
	 * @author cyf
	 * @date 2017年9月11日 上午9:25:10
	 */
	public static String formatyyyyMMddHHmmss(Date date) {
		return dateTimePattern2.format(date);
	}
	
	public static Date formatyyyyMMdd(String date) throws ParseException {
		return datePattern.parse(date);
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 生成20170911格式时间
	 * @author cyf
	 * @throws ParseException
	 * @date 2017年9月11日 上午9:25:10
	 */
	public static Date parseyyyyMMdd(String date) throws ParseException {
		return dfyyyyMMdd.parse(date);
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 获取过去的月份
	 * @author cyf
	 * @date 2017年10月24日 下午5:26:19
	 */
	public static String getPastMonth(int i, Date searchDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(searchDate);
		c.add(Calendar.MONTH, -i);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String time = format.format(c.getTime());
		return time;
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 获取过去的月份
	 * @author cyf
	 * @date 2017年10月24日 下午5:26:19
	 */
	public static Date getPastMonthDate(int i, Date searchDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(searchDate);
		c.add(Calendar.MONTH, -i);
		return c.getTime();
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 指定月第一天
	 * @author cyf
	 * @date 2017年10月24日 下午6:54:34
	 */
	public static String getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1; // 上个月月份
		int year = cal.get(Calendar.YEAR);
		String mon = "";
		if (month < 10) {
			mon = "0" + String.valueOf(month);
		} else {
			mon = String.valueOf(month);
		}
		String endDay = year + "-" + mon + "-01";
		return endDay;
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 指定月最后一天
	 * @author cyf
	 * @date 2017年10月24日 下午6:54:50
	 */
	public static String getMonthLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1; // 上个月
		int day = getMonthDayLenth(date);
		int year = cal.get(Calendar.YEAR);
		String endDay = year + "-" + month + "-" + day;
		return endDay;
	}
	
	/**
	 * @Package com.kingtopinfo.base.util
	 * @Description: 获取本月天数
	 * @author cyf
	 * @date 2017年10月27日 下午3:55:24
	 */
	public static int getMonthDayLenth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		return calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	 * @param date 需要增减的日期
	 * @param field 增减的单位(其格式包括：年-DateUtil.YEAR,月-DateUtil.MONTH,日-DateUtil.DAY,时-DateUtil.HOUR,分-DateUtil.MINUTE,秒-DateUtil.SECOND)
	 * @param amount 日期增减的时间量，+代表增减，-代表减少
	 * @return 
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	/**
	 *  格式化时间为指定时间格式的时间字符串
	 * @param date 需要格式化的日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	* @Package com.kingtopinfo.base.util  
	* @Description: 获取下个月0点0分0秒
	* @author cyf    
	 * @param startDate 
	 * @return 
	* @date 2018年1月11日 上午9:20:38
	 */
	public static Date searchNextDay(Date startDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	* @Package com.kingtopinfo.base.util  
	* @Description: yyyy-MM-dd HH:mm:ss
	* @author cyf    
	* @date 2018年3月5日 下午4:18:16
	 */
	public static String formatDateTime(Date date) {
		return dateTimePattern.format(date);
	}
	
}