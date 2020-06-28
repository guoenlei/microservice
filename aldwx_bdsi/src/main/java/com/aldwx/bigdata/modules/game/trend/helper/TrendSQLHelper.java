package com.aldwx.bigdata.modules.game.trend.helper;

public class TrendSQLHelper {
    public static String totalSQL(String dateStart,String dateEnd,String ak){
        String sql=" SELECT app_key,sum(new_comer_count) new_count,sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_trend_analysis " +
                "      WHERE day <='" + dateEnd+"' and day>='"
                + dateStart+"' " +
                " and app_key='"+ ak+"' group by app_key";
        return sql;
    }
    public static String hourSQL(String dateStart,String dateEnd,String ak){
        String sql=" SELECT app_key,hour,sum(new_comer_count) new_count,sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_hourly_trend_analysis " +
                "      WHERE day <='" + dateEnd+"' and day>='"
                + dateStart+"' " +
                " and app_key='"+ ak+"' group by app_key,hour order by hour";
        return sql;
    }
    public static String daySQL(String dateStart, String dateEnd, String ak){
        String sql=" SELECT app_key,day,sum(new_comer_count) new_count,sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_trend_analysis " +
                "      WHERE day <='" + dateEnd +"' and day>='"
                + dateStart+"' " +
                " and app_key='"+ ak+"' group by app_key,day order by day";
        return sql;
    }
    public static String weekSQL(String dateStart,String dateEnd,String ak ){
        String sql=" SELECT app_key,date_format(day,'%x-%v') week,sum(new_comer_count) new_count,sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_trend_analysis " +
                "      WHERE day <='" + dateEnd +"' and day>='"
                + dateStart +"' " +
                " and app_key='"+ ak+"' group by app_key,week order by week";
        return sql;
    }
    public static String monthSQL(String dateStart,String dateEnd,String ak){
        String sql=" SELECT app_key,date_format(day,'%Y-%m') mon,sum(new_comer_count) new_count,sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_hourly_trend_analysis " +
                "      WHERE day <='" + dateEnd+"' and day>='"
                + dateStart+"' " +
                " and app_key='"+ ak+"' group by app_key,mon order by mon";
        return sql;
    }

  /*  public static String weekSQL1(String dateStart,String dateEnd,String ak,String week ){
        String sql=" SELECT app_key,sum(new_comer_count) new_count,"+week+" as week,+sum(visitor_count) visit_count,sum(total_page_count) total_page_count,sum(open_count) open_count,sum(total_stay_time)/sum(open_count) secondary_avg_stay_time,sum(one_page_count)/sum(open_count) bounce_rate from aldstat_trend_analysis " +
                "      WHERE day <='" + dateEnd +"' and day>='"
                + dateStart +"' " +
                " and app_key='"+ ak+"' group by app_key ";
        return sql;
    }*/
}
