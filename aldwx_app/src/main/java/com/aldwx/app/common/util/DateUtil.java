package com.aldwx.app.common.util;

import com.facebook.presto.jdbc.internal.guava.collect.Lists;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


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
		return getBeforeDay(getTodayDate());
	}

	/**
	 * 获取过去N天日期
	 * @param intervals
	 * @return
	 */
	public static ArrayList<String> getBeforeDate(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
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

	//获取当前时间的年月日
	public  static  String getNowDaytr(){
		Date dNow = new Date();   //当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(dNow);    //格式化前一天
		return  defaultStartDate;
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
	//获取系统时间的时间戳
	public static Long SystemTemstap(){
		Date strtodate = new Date();
		Long a=strtodate.getTime()/1000L;
		return  a;
	}
	//获取时间戳转date
	public static Date temstapToDate(Long time){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time*1000);
		return  date;
	}
	//获取时间戳转date
	public static Date temstapToDateNoYMD(Long time){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(time*1000);
		return  date;
	}
	//时间戳转年月日
	public static String tepmToString(Long time) {
		Date date=new Date(time*1000);
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
		String times=formatter.format(date);
		return times;
	}


	/**
	 * string 转 long
	 * @param time
	 * @return
	 */
	public static Long getTimeToLong(String time) {
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date  = TIME_FORMAT.parse(time);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}


	/**
	 * 前一天
	 * @param d
	 * @return
	 */
	public static String getBeforeDay(String d) {
		String  a = "";
		try {
			Date date = DATE_FORMAT.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			a =DATE_FORMAT.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;
	}
	/**
	 * 前N天
	 * @param d
	 * @return
	 */
	public static String getBeforeDayByN(String d,int n) {
		String  a = "";
		try {
			Date date = DATE_FORMAT.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -n);
			date = calendar.getTime();
			a =DATE_FORMAT.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;
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
	/**
	 * 传字符串 返回时间戳  并截取长度
	 * @param time
	 * @return
	 */
	public static Long strToLong(String time){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(time, pos);
		Long a=strtodate.getTime()/1000L;
		return a;
	}


	//获取一天的起始时间和结束时间
	public static List<String>  getNowDaystrToEnd(){
		List<String>  list =new ArrayList();
		Date dNow = new Date();   //当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(dNow);    //格式化前一天
		defaultStartDate = defaultStartDate+" 00:00:00";
		String defaultEndDate = defaultStartDate.substring(0,10)+" 23:59:59";
/*		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos2 = new ParsePosition(0);
		Date timeStart = TIME_FORMAT.parse(defaultStartDate, pos);
		Date timeEnd = TIME_FORMAT.parse(defaultEndDate, pos2);*/
		list.add(defaultStartDate);
		list.add(defaultEndDate);
		return  list;
	}
	
 /**
     * 获取最近一周开始和结束日期
     * @return
     */
	public static String[] getNearly7Day() {
		ArrayList<String> dates = getBeforeDate(7);
		String[] _dates = new String[2];
		if(null != dates) {
			_dates[1] = dates.get(0);
			_dates[0] = dates.get(6);
		}

		return _dates;
	}
//获取当前时间的年月日
	public  static  String dateTranStr(Date date){
		//当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(date);    //格式化前一天
		return  defaultStartDate;
	}
    /**
     * 获取最近一月开始和结束日期
     * @return
     */
    public static String[] getNearly30Day() {
        ArrayList<String> dates = getBeforeDate(30);
        String[] _dates = new String[2];
        if(null != dates) {
            _dates[1] = dates.get(0);
            _dates[0] = dates.get(29);
        }

        return _dates;
    }

/*
* 时间按自然周切割
* */
public  static List<String> getWeekList(List<String> listTime) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatmon = new SimpleDateFormat("MM");
	Date date = null;
	List<String> list=new ArrayList<>();
	try {
		Map<String,String> map=new LinkedHashMap<>();
		for (int i=0;i<listTime.size();i++){
			date = format.parse(listTime.get(i));
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			int year=calendar.get(Calendar.YEAR);
			int mon=Integer.parseInt(formatmon.format(date));
			int num=calendar.get(Calendar.WEEK_OF_YEAR);
			if (num==1&&mon==12){
				year =year+1;
			}
			String week="";
			if (num>0&&num<10){
				week="0"+num;
			}else {
				week=""+num;
			}
			map.put(listTime.get(i),year+week);
		}
		Map<String, ArrayList<String>> map2 = new LinkedHashMap<>();
		String entryValue = null;
		String entryKay = null;
		ArrayList<String> tmpValue = new ArrayList<>();
		ArrayList<String> tmpMap2Value = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			tmpValue.clear();
			tmpMap2Value.clear();
			entryKay = entry.getKey();
			entryValue = entry.getValue();
			if (map2.keySet().contains(entryValue)) {
				tmpMap2Value = map2.get(entryValue);
				tmpMap2Value.add(entryKay);
				map2.put(entryValue, (ArrayList<String>) tmpMap2Value.clone());
			} else {
				tmpValue.add(entryKay);
				map2.put(entryValue, (ArrayList<String>) tmpValue.clone());
			}
		}
		Iterator entries = map2.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			List<String> value = (List<String>) entry.getValue();
			String a=value.get(0)+"~"+value.get(value.size()-1);
			list.add(a);
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return  list;

}
	/*
	 * 判断是不是周日
	 * */
	public static Boolean isSunDay(String time){
		Boolean res=null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1){
				res=true;
			}else {
				res=false;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return res;
	}
	/*
	 * 获取自然周的周一
	 * */
	public static String getMonday(String time){
		String str=null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(time);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
			if (day_of_week == 0){
				day_of_week = 7;
			}
			c.add(Calendar.DATE, -day_of_week + 1);
			str = format.format(c.getTime());
		}catch (Exception e){
			e.printStackTrace();
		}
		return str;
	}


	/*
	 * 获取当前的小时
	 * */

	public static Integer getNowHour(){
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.HOUR_OF_DAY);
		return date;
	}

	public static void main(String[] args) {
try{

	System.out.println(getWeekList(getBetweenDates3("2019-06-01","2019-06-28")));
}catch (Exception e){

}
	}
}