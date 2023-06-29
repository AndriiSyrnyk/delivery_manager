package dodomu.deliverymanager.utils;

import java.sql.Date;
import java.util.List;

public class DateTimeUtil {
    public static int getYearFromYearMonthString(String yearMonth) {
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        return year;
    }
    public static int getMonthFromYearMonthString(String yearMonth) {
        int month = Integer.parseInt(yearMonth.substring(5, 7));
        return month;
    }

//    public static List<Date> getDateListByCertainYearAndMonth(String yearMonth) {
//
//    }
}
