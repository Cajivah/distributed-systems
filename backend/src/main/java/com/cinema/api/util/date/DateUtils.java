package com.cinema.api.util.date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

     private static DateTimeFormatter humanFormatterWithoutSecs = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");

     public static String toHumanFormatWithoutSecs(ZonedDateTime zonedDateTime) {
          return zonedDateTime.format(humanFormatterWithoutSecs);
     }
}
