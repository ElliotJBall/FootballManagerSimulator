package com.elliot.footballmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class to provide helper methods simplifying dealing with the date object.
 *
 * @author Elliot
 */
public class DateUtils {

  public static SimpleDateFormat FIXTURE_DATE_FORMAT = new SimpleDateFormat(
      "EEE MMM d HH:mm:ss zzz yyyy");
  public static SimpleDateFormat FIXTURE_DATE_DISPLAY_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
  private static final Calendar calendar = Calendar.getInstance();

  public DateUtils() {

  }

  public static Date addDays(Date date, int days) {
    calendar.setTime(date);
    calendar.add(Calendar.DATE, days);
    return calendar.getTime();
  }
}
