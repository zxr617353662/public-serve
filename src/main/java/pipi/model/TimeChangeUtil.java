package pipi.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeChangeUtil {
    public static String changeTime(LocalDateTime dateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateformat.format(date);
        return time;
    }
}
