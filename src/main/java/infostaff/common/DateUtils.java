package infostaff.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Returns today's date as java.util.Date object
     *
     * @return today's date as java.util.Date object
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Returns today's date as yyyy-MM-dd format
     *
     * @return today's date as yyyy-MM-dd format
     */
    public static String todayStr() {
        return sdf.format(today());
    }

    /**
     * Returns the formatted String date for the passed java.util.Date object
     *
     * @param date
     * @return
     */
    public static String formattedDate(Date date) {
        return date != null ? sdf.format(date) : todayStr();
    }

    public static Date convertStringToDate(String strDate, String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            // you can change format of date
            Date date = formatter.parse(strDate);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String convertDateToString(Date date, String format) {

        if (date == null)
            return null;

        return new SimpleDateFormat(format).format(date);
    }

}
