package quanly_anything_you_want.manage.com.quanlyanything.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static String formatDate(Date date, String desFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateResultFormat = new SimpleDateFormat(desFormat, Locale.US);
        dateResultFormat.setTimeZone(TimeZone.getDefault());
        return dateResultFormat.format(date);
    }

    public static String formatFullDate(Date date) {
        String dateFormat = "MM/dd/yyyy";
        dateFormat += "   HH:mm";
        return formatDate(date, dateFormat);
    }

    public static String formatFullDateVN(Date date) {
        String dateFormat = "HH:mm - ";
        dateFormat += "dd/MM/yyyy";
        return formatDate(date, dateFormat);
    }

    public static String formatDate(Date date) {
        String dateFormat = "MM/dd/yyyy";
        return formatDate(date, dateFormat);
    }

    public static String formatDateVN(Date date) {
        String dateFormat = "dd/MM/yyyy";
        return formatDate(date, dateFormat);
    }
    public static String formatDateVNMonthYear(Date date) {
        String dateFormat = "MM - yyyy";
        return "Tháng "+ formatDate(date, dateFormat);
    }

    public static String formatFullDatePeriods(Date date) {
        String dateFormat = "MM/dd/yyyy";
        dateFormat += " HH:mm a";
        return formatDate(date, dateFormat);
    }

    public static String formatDateAsHourMinute(Date date) {
        String dateFormat = "HH:mm";
        return formatDate(date, dateFormat);
    }

    public static String formatDateAsHourMinutePeriods(Date date) {
        String dateFormat = "HH:mm a";
        return formatDate(date, dateFormat);
    }

    // get current date as date
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    // get current day in month as number
    public static int getCurrentDayInMonth() {
        Date date = getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    // get current month as number
    public static int getCurrentMonth() {
        Date date = getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    // get current year as number
    public static int getCurrentYear() {
        Date date = getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    // get month of date as number
    public static int getMonthOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    // get year of date as number
    public static int getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    // get day of date as number
    public static int getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    // get hours of date as number
    public static int getHoursOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    // get minutes of date as number
    public static int getMinutesOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    // get second of date as number
    public static int getSecondOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    public static Date createDateFromDMY(int date, int month, int year) {
        Calendar cal = Calendar.getInstance();

        // reset time
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        cal.set(Calendar.DATE, date);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }

    public static Date createDateFromDMYHM(int date, int month, int year, int hours, int minutes) {
        Calendar cal = Calendar.getInstance();

        // reset time
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.MILLISECOND, 0);

        cal.set(Calendar.DATE, date);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day < 1 || day > 31) {
            return false;
        }
        if (month < 0 || month > 11) {
            return false;
        }
        if (year < 0) {
            return false;
        }
        if (day < 29) {
            return true;
        }
        switch (month) {
            case 1:
                return day == 29 && isLeapYear(year);
            case 3:
            case 5:
            case 8:
            case 10:
                return day != 31;
            default:
                return true;
        }
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public static Date updateDMYForDate(Date date, Date dmyDate) {
        Calendar dmyCal = Calendar.getInstance();
        dmyCal.setTime(dmyDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, dmyCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, dmyCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, dmyCal.get(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date updateHMSMLForDate(Date date, int hours, int minutes, int seconds, int milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        cal.set(Calendar.MILLISECOND, milliseconds);
        return cal.getTime();
    }

    public static Date convertTimeZone(Date date, TimeZone fromTZ, TimeZone toTZ) {
        long fromTZDst = 0;
        if (fromTZ.inDaylightTime(date)) {
            fromTZDst = fromTZ.getDSTSavings();
        }

        long fromTZOffset = fromTZ.getRawOffset() + fromTZDst;

        long toTZDst = 0;
        if (toTZ.inDaylightTime(date)) {
            toTZDst = toTZ.getDSTSavings();
        }
        long toTZOffset = toTZ.getRawOffset() + toTZDst;

        return new Date(date.getTime() + (toTZOffset - fromTZOffset));
    }


    public static TimeZone getDefaultTimeZone() {
        return TimeZone.getDefault();
    }


}
