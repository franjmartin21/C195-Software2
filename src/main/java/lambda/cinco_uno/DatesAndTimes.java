package lambda.cinco_uno;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DatesAndTimes {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
        //*** CREATION OF LOCAL DATE
        System.out.println("*** CREATION OF LOCALDATE");
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);
        //*** CREATION OF LOCAL TIME
        System.out.println("*** CREATION OF LOCALTIME");
        LocalTime time1 =  LocalTime.of(6, 15);               // hour and minute
        LocalTime time2 =  LocalTime.of(6, 15, 30);           // + seconds
        LocalTime time3 =  LocalTime.of(6, 15, 30, 200);
        //*** Combining both things to create LOCALDATETIME
        System.out.println("*** CREATION OF LOCALDATETIME");
        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
        //*** CREATION OF ZONEDDATETIME
        System.out.println("*** CREATION OF ZONEDDATETIME");
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20,
                6, 15, 30, 200, zone);
        ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
        System.out.println(zone);
        //*** FINDING ALL TIMEZONES AVAILABLE
        System.out.println("*** FINDING ALL TIMEZONES AVAILABLE");
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("Europe"))
                .sorted().forEach(System.out::println);
        /*
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("US") || z.contains("America"))
                .sorted().forEach(System.out::println);
         */
        //*** MANIPULATING DATES AND TIMES
        System.out.println("*** MANIPULATING DATES AND TIMES");
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        System.out.println(date);          // 2014–01–20
        date = date.plusDays(2);
        System.out.println(date);          // 2014–01–22
        date = date.plusWeeks(1);
        System.out.println(date);          // 2014–01–29
        date = date.plusMonths(1);
        System.out.println(date);          // 2014–02–28
        date = date.plusYears(5);
        System.out.println(date);
        //Chaining methods
        LocalDate date_chaining = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time_chaining = LocalTime.of(5, 15);
        LocalDateTime dateTime_chaining = LocalDateTime.of(date_chaining, time_chaining)
                .minusDays(1).minusHours(10).minusSeconds(30);
        System.out.println(dateTime_chaining);
        //*** Working with Periods
        System.out.println("*** Working with Periods");
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1);          // create a period
        performAnimalEnrichment(start, end, period);
        //Creating periods
        Period annually = Period.ofYears(1);               // every 1 year
        Period quarterly = Period.ofMonths(3);             // every 3 months
        Period everyThreeWeeks = Period.ofWeeks(3);        // every 3 weeks
        Period everyOtherDay = Period.ofDays(2);           // every 2 days
        Period everyYearAndAWeek = Period.of(1, 0, 7);
        //you cannot chain in this case
        Period wrong = Period.ofYears(1).ofWeeks(1);          // every week

        //*** Working with Durations (like Period but smaller units)
        System.out.println("*** Working with Durations");
        Duration daily = Duration.ofDays(1);                // PT24H
        Duration hourly = Duration.ofHours(1);              // PT1H
        Duration everyMinute = Duration.ofMinutes(1);       // PT1M
        Duration everyTenSeconds = Duration.ofSeconds(10);  // PT10S
        Duration everyMilli = Duration.ofMillis(1);         // PT0.001S
        Duration everyNano = Duration.ofNanos(1);           // PT0.000000001S
        //or
        daily = Duration.of(1, ChronoUnit.DAYS);
        hourly = Duration.of(1, ChronoUnit.HOURS);
        everyMinute = Duration.of(1, ChronoUnit.MINUTES);
        everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
        everyMilli = Duration.of(1, ChronoUnit.MILLIS);
        everyNano = Duration.of(1, ChronoUnit.NANOS);
        //Chrono unit differences
        LocalTime one_chrono = LocalTime.of(5, 15);
        LocalTime two_chrono = LocalTime.of(6, 30);
        LocalDate date_chrono = LocalDate.of(2016, 1, 20);

        System.out.println(ChronoUnit.HOURS.between(one_chrono, two_chrono));   // 1
        System.out.println(ChronoUnit.MINUTES.between(one_chrono, two_chrono)); // 75
        //System.out.println(ChronoUnit.MINUTES.between(one_chrono, date_chrono));// DateTimeException
        //Using Chrono
        LocalDate date_usingchrono = LocalDate.of(2015, 1, 20);
        LocalTime time_usingchrono = LocalTime.of(6, 15);
        LocalDateTime dateTime_usingchrono = LocalDateTime.of(date_usingchrono, time_usingchrono);
        Duration duration = Duration.ofHours(6);
        System.out.println(dateTime_usingchrono.plus(duration)); // 2015–01–20T12:15
        System.out.println(time_usingchrono.plus(duration));     // 12:15
        //System.out.println(date_usingchrono.plus(duration));     // UnsupportedTemporalException

        //Period and Duration are not equivalent
        LocalDate date_eq = LocalDate.of(2015, 5, 25);
        Period period_eq = Period.ofDays(1);
        Duration days_eq = Duration.ofDays(1);

        System.out.println(date_eq.plus(period_eq)); // 2015–05–26
        //System.out.println(date_eq.plus(days_eq)); // Unsupported unit: Seconds

        //*** WORKING WITH INSTANTS(Instant is GMT)
        System.out.println("*** WORKING WITH INSTANTS");
        Instant now = Instant.now();
        // do something time consuming
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant later = Instant.now();
        Duration duration_instant = Duration.between(now, later);
        System.out.println(duration_instant.toMillis());
        //ZonedDateTime, you can turn it into an Instant:
        LocalDate date_instant = LocalDate.of(2015, 5, 25);
        LocalTime time_instant  = LocalTime.of(11, 55, 00);
        ZoneId zone_instant  = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date_instant , time_instant , zone_instant);
        Instant instant = zonedDateTime.toInstant(); // 2015–05–25T15:55:00Z
        System.out.println(zonedDateTime);           // 2015–05–25T11:55–04:00[US/Eastern]
        System.out.println(instant);                 // 2015–05–25T15:55:00Z
        //From epoch time
        System.out.println("*** Instant Epoch");
        Instant instant_epoch = Instant.ofEpochSecond(1516191905);
        System.out.println(instant_epoch);
        //calculations with instant
        Instant nextDay = instant.plus(1, ChronoUnit.DAYS);
        System.out.println(nextDay);
        Instant nextHour = instant.plus(1, ChronoUnit.HOURS);
        System.out.println(nextHour);
        //Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // exception
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)){
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plus(period);                 // adds the period
        }
    }
}
