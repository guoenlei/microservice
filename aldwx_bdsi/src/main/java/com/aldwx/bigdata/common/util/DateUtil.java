package com.aldwx.bigdata.common.util;

import com.facebook.presto.jdbc.internal.guava.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日期处理
 * @author lux@chinatelecom.cn
 * @createTime 2017年1月12日下午3:18:52
 * @version
 */
public class DateUtil {

	public static final SimpleDateFormat TIME_FORMAT =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT =
			new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATEKEY_FORMAT =
			new SimpleDateFormat("yyyyMMdd");

	public static final SimpleDateFormat DATEKEY_FORMAT_2 =
			new SimpleDateFormat("yyyy/MM/dd");

	private static final Integer ONE_DAY = 1;


	/**
	 * 获取当天日期（yyyy-MM-dd）
	 * @return 当天日期
	 */
	public static String getTodayDate() {
		return DATE_FORMAT.format(new Date());
	}


	/**
	 * 获取昨天
	 * @return
	 */
	public static String getYesterday() {
		return getBeforeDate(1).get(0);
	}

	/**
	 * 获取过去N天日期
	 * @param intervals
	 * @return
	 */
	public static ArrayList<String> getBeforeDate(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i <intervals; i++) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}


	/**
	 * 获取未来N天日期
	 * @param intervals
	 * @return
	 */
	public static ArrayList<String> getFutureDate(int intervals) {
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i <intervals; i++) {
			fetureDaysList.add(getFetureDate(i));
		}
		return fetureDaysList;
	}


	/**
	 * 获取过去第几天的日期 
	 *
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past - ONE_DAY);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}


	/**
	 * 获取未来 第 past 天的日期 
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past + ONE_DAY);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}


	/**
	 * 获取最近一周开始和结束日期
	 * @return
	 */
	public static String[] getFirstAndEndDate() {
		ArrayList<String> dates = getBeforeDate(7);
		String[] _dates = new String[2];
		if(null != dates) {
			_dates[0] = dates.get(0);
			_dates[1] = dates.get(6);
		}

		return _dates;
	}
	public static String[] getNearly7Day() {
		ArrayList<String> dates = getBeforeDate(7);
		String[] _dates = new String[2];
		if(null != dates) {
			_dates[0] = dates.get(0);
			_dates[1] = dates.get(6);
		}

		return _dates;
	}
	/**
	 * 获取最近一月开始和结束日期
	 * @return
	 */
	public static String[] getNearly30Day() {
		ArrayList<String> dates = getBeforeDate(30);
		String[] _dates = new String[2];
		if(null != dates) {
			_dates[0] = dates.get(0);
			_dates[1] = dates.get(29);
		}

		return _dates;
	}



	/**
	 * 获取两个日期之间的日期
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(String startTime, String endTime) {
		Date start = null;
		Date end = null;
		try {
			start = DATEKEY_FORMAT.parse(startTime);
			end = DATEKEY_FORMAT.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Date> result = new ArrayList<Date>();
		result.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}

		result.add(end);
		return result;
	}

	/**
	 * 获取两个日期之间的日期
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates2(String startTime, String endTime) {
		Date start = null;
		Date end = null;
		List<Date> result = new ArrayList<Date>();
		try {
			start = DATE_FORMAT.parse(startTime);
			end = DATE_FORMAT.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		result.add(start);
		if(startTime.equals(endTime)) {
			return result;
		}
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			//剔除今天的日期
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}

		result.add(end);
		return result;
	}




	/**
	 * 获取两个日期之间的日期
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return 日期集合
	 */
	public static List<String> getBetweenDates3(String startTime, String endTime) {
		Date start = null;
		Date end = null;
		List<String> result = new ArrayList<String>();
		try {
			start = DATE_FORMAT.parse(startTime);
			end = DATE_FORMAT.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		result.add(startTime);
		if(startTime.equals(endTime)) {
			return result;
		}
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			//剔除今天的日期
			result.add(DATE_FORMAT.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}

		result.add(DATE_FORMAT.format(end));
		return result;
	}



	public static List<Date> deteleTodayTime(List<Date> dateList) {
		List<Date> dlist = Lists.newArrayList();
		if(null != dateList && dateList.size() > 0) {
			for(Date date : dateList) {

			}
		}
		return dlist;
	}



	/**
	 * 前一天
	 * @param d
	 * @return
	 */
	public static Date getBeforeDay(String d) {
		Date date = null;
		try {
			date = DATEKEY_FORMAT.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 后一天
	 * @param d
	 * @return
	 */
	public static Date getNextDay(String d) {
		Date date = null;
		try {
			date = DATEKEY_FORMAT.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, +1);
			date = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * @param date
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(String date) {
		String[] dayType = {"holiday", "work", "work", "work", "work", "work", "holiday"};
//		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		int w = 0;
		try {
			Date dt = DATEKEY_FORMAT.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		return weekDays[w];
		return dayType[w];
	}


	/**
	 * 计算两个日期之间的日期，并分组
	 * 如startTime 20180711 endTime 20180801
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<String> getBetweenDays(String startTime, String endTime) {
		List<Date> days = getBetweenDates(startTime, endTime);
		List<String> dayList = new ArrayList<>();
		String start = null;
		String end = null;
		if(null != days && days.size() > 0) {
			for(int i = 0; i < days.size(); i++) {
				start = DATE_FORMAT.format(days.get(i));
				if(days.size() == 1) {
					end = DATE_FORMAT.format(days.get(i));
				} else {
					for(int j = i+1; j < days.size(); j++) {
						end = DATE_FORMAT.format(days.get(j));
						dayList.add(start + "~" + end);
						break;
					}
				}
			}
		}
		return dayList;
	}


	/**
	 * 判断时间段内是否包含今天的日期
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isContainsToday(String startTime, String endTime) {
		String start = null;
		String end = null;
		if(startTime.contains("-")) {
			start = startTime.replaceAll("-", "");
			end = endTime.replaceAll("-", "");
		} else {
			start = startTime;
			end = endTime;
		}
		boolean isContains = false;
		List<Date> days = getBetweenDates(start, end);
		if(null != days && days.size() > 0) {
			for(Date date : days) {
				String formatDate = DATE_FORMAT.format(date);
				if(formatDate.contains(getTodayDate())) {
					isContains = true;
					break;
				}
			}
		}
		return isContains;
//		return true;
	}
	public static String getHourRange(String a){
		String hour="";
		if (Integer.parseInt(a)==0) {
			hour="00:00 - 00:59";
		}else if (Integer.parseInt(a)==1) {
			hour="01:00 - 01:59";
		}else if (Integer.parseInt(a)==2) {
			hour="02:00 - 02:59";
		}else if (Integer.parseInt(a)==3) {
			hour="03:00 - 03:59";
		}else if (Integer.parseInt(a)==4) {
			hour="04:00 - 04:59";
		}else if (Integer.parseInt(a)==5) {
			hour="05:00 - 05:59";
		}else if (Integer.parseInt(a)==6) {
			hour="06:00 - 06:59";
		}else if (Integer.parseInt(a)==7) {
			hour="07:00 - 07:59";
		}else if (Integer.parseInt(a)==8) {
			hour="08:00 - 08:59";
		}else if (Integer.parseInt(a)==9) {
			hour="09:00 - 09:59";
		}else if (Integer.parseInt(a)==10) {
			hour="10:00 - 10:59";
		}else if (Integer.parseInt(a)==11) {
			hour="11:00 - 11:59";
		}else if (Integer.parseInt(a)==12) {
			hour="12:00 - 12:59";
		}else if (Integer.parseInt(a)==13) {
			hour="13:00 - 13:59";
		}else if (Integer.parseInt(a)==14) {
			hour="14:00 - 14:59";
		}else if (Integer.parseInt(a)==15) {
			hour="15:00 - 15:59";
		}else if (Integer.parseInt(a)==16) {
			hour="16:00 - 16:59";
		}else if (Integer.parseInt(a)==17) {
			hour="17:00 - 17:59";
		}else if (Integer.parseInt(a)==18) {
			hour="18:00 - 18:59";
		}else if (Integer.parseInt(a)==19) {
			hour="19:00 - 19:59";
		}else if (Integer.parseInt(a)==20) {
			hour="20:00 - 20:59";
		}else if (Integer.parseInt(a)==21) {
			hour="21:00 - 21:59";
		}else if (Integer.parseInt(a)==22) {
			hour="22:00 - 22:59";
		}else if (Integer.parseInt(a)==23) {
			hour="23:00 - 23:59";
		}
		return hour;
	}
	public  static String getWeekNum(String aa) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatmon = new SimpleDateFormat("MM");
		Date date = null;
		try {
			date = format.parse(aa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int year=calendar.get(Calendar.YEAR);
		int mon=Integer.parseInt(formatmon.format(date));
		int num=calendar.get(Calendar.WEEK_OF_YEAR);
		String a="";
		if (num==1&&mon==12){
			year =year+1;
		}
		String month="";
		if (num>0&&num<10){
			month="0"+num;
		}else {
			month=""+num;
		}
		return  year+"-"+month;

	}
	public static void main(String[] args) throws ParseException {
//		List<Date> dates = getBetweenDates2("2018-10-11", "2018-10-13");
//		for(Date date : dates) {
//			String s = DATE_FORMAT.format(date);
//			System.out.println(s);
//		}
		String a="2018-12-30";
		System.out.println(getWeekNum(a));
//		System.out.println(getYesterday());
	//	System.out.println(DateUtil.getBetweenDates3("2018-11-01", "2018-11-05"));
//		System.out.println(DateUtil.getBetweenDates2("2018-10-11", "2018-10-13"));
		//System.out.println(DateUtil.getWeekNum("2019-04-16"));;
//		System.out.println(DateUtil.getBeforeDate(2));

	}

}

