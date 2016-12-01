package ru.emitrohin.votingsystem.util;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalTime VOTING_TIME = LocalTime.of(11, 0);

    private static Clock clock = Clock.systemDefaultZone();
    private static ZoneId zoneId = ZoneId.systemDefault();


    private TimeUtil() {
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }

    public static LocalDateTime parseLocalDateTime(String str) {
        return parseLocalDateTime(str, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String str, DateTimeFormatter formatter) {
        return StringUtils.isEmpty(str) ? LocalDateTime.now() : LocalDateTime.parse(str, formatter);
    }

    public static LocalDate now() {
        return LocalDate.now(getClock());
    }

    public static LocalTime nowTime() {
        return LocalTime.now(getClock());
    }

    public static void useFixedClockAt(LocalDateTime date) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
    }

    public static void useSystemDefaultZoneClock() {
        clock = Clock.systemDefaultZone();
    }

    private static Clock getClock() {
        return clock;
    }
}