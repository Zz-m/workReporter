package com.adj.workreporter.util;

import java.time.*;

/**
 * 日期时间工具
 * Created by dhx on 2017/4/13.
 */
public class DateTimeUtil {

    public static long getWeekStartEpochSecond(long someMoment) {
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(someMoment), ZoneId.systemDefault());
        LocalDateTime weekBegin = dateTime.with(DayOfWeek.MONDAY).toLocalDateTime().with(LocalTime.MIN);
        return weekBegin.toEpochSecond(ZoneId.systemDefault().getRules().getOffset(weekBegin));
    }

    public static long getWeekEndEpochSecond(long someMoment) {
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(someMoment), ZoneId.systemDefault());
        LocalDateTime weekBegin = dateTime.with(DayOfWeek.SUNDAY).toLocalDateTime().with(LocalTime.MAX);
        return weekBegin.toEpochSecond(ZoneId.systemDefault().getRules().getOffset(weekBegin));
    }
}
