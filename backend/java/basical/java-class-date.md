## java 日期

### java7

```java
/**
   * 字符串转换为java.util.Date<br>
   * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
   * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
   * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
   * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
   * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
   * @param time String 字符串<br>
   * @return Date 日期<br>
   */
public static Date string2Date(String time){
    SimpleDateFormat formatter;
    int tempPos=time.indexOf("AD") ;
    time=time.trim() ;
    formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
    if(tempPos>-1){
      time=time.substring(0,tempPos)+
           "公元"+time.substring(tempPos+"AD".length());//china
      formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
    }
    tempPos=time.indexOf("-");
    if(tempPos>-1&&(time.indexOf(" ")<0)){
      formatter = new SimpleDateFormat ("yyyyMMddHHmmssZ");
    }
    else if((time.indexOf("/")>-1) &&(time.indexOf(" ")>-1)){
      formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
    }
    else if((time.indexOf("-")>-1) &&(time.indexOf(" ")>-1)){
      formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    }
    else if((time.indexOf("/")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
      formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
    }
    else if((time.indexOf("-")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
      formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
    }
    ParsePosition pos = new ParsePosition(0);
    java.util.Date ctime = formatter.parse(time, pos);

    return ctime;
}


/**
   * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)<br>
   * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'<br>
   * @param time Date 日期<br>
   * @return String   字符串<br>
   */
public static String date2String(Date time){
    SimpleDateFormat formatter;
    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    String ctime = formatter.format(time);

    return ctime;
}
```

### java8

1. **Get timestamp**

   ```java
   // Java 8
   Clock.systemDefaultZone().millis();
   Instant timestamp = Instant.now();

   // Before
   Calendar.getInstance().getTimeInMillis();
   System.currentTimeMillis();
   ```

2. **Format DateTime**

```java
// Java 8
DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
// Before
SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd");
```

3. **Instant/Duration**

   ```java
   Instant timestamp = Instant.now();
   Duration thirtyDay = Duration.ofDay(30);
   ```

4. LocalDate

   ```java
   LocalDate today = LocalDate.now();
   today.isLeapYear();
   today.isBefore(LocalDate.of(2015,1,1));
   today.atTime(LocalTime.now());
   today.PlusDays(10);
   today.plusWeeks(3);
   today.plusMonths(20);
   today.minusDays(10);
   today.minusWeeks(3);
   today.minusMonths(20);
   LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1);
   today.with(TempolarAdjusters.firstDayOfMonth());
   today.with(TempolarAdjusters.lastDayOfYear());
   Peroid period = today.until(TempolarAdjusters.lastDayOfYear());

   // Before
   Calendar c = Calendar.getInstance();
   c.add(Calendar.MONTH, 0);
   c.set(Calendar.DAY_OF_MONTH,1);
   String first = format.format(c.getTime());

   Calendar ca = Calendar.getInstance();
   ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
   String last = format.format(ca.getTime());
   ```

5. Utils.calss

   ```java
   // util class: time 时间, date： 日期
   public class TimeUtils {

       private static Logger logger = LoggerFactory.getLogger(TimeUtils.class);

        /**
        * fn: convert timestamp to LocalDateTime
        * @param: timestamp
        * @return: LocalDateTime
        */
       public static LocalDateTime timestamp2LocalDateTime(long timestamp) {

           Instant instant = Instant.ofEpochMilli(timestamp);
           ZoneId zone = ZoneId.systemDefault();
           return LocalDateTime.ofInstant(instant, zone);
       }

       /**
        * fn: convert LocalDateTime to timestamp
        * @param: LocalDateTime
        * @return: Long
        */
       public static Long localDateTime2Timestamp(LocalDateTime localDateTime) {

           return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
       }

        /**
        * LocalDate 转换为 Date
        * @param LocalDate
        * @return: Date
        */
       public static Date localDate2Date(LocalDate date) {

           ZoneId zone = ZoneId.systemDefault();
           Instant instant = date.atStartOfDay().atZone(zone).toInstant();
           return Date.from(instant);
       }

       /**
        * Date 转换为 LocalDate
        * @param date
        * @return: LocalDate
        */
       public static LocalDate date2LocalDate(Date date) {
       }

        /**
        * Date 转换为 LocalDateTime
        * @param date
        * @return: LocalDateTime
        */
        public static LocalDateTime date2LocalDateTime(Date dateTime) {

           Instant instant = dateTime.toInstant();
           ZoneId zoneId = ZoneId.systemDefault();
           LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

           return localDateTime;
       }

        /**
        * localDateTime 转换为 Date
        * @param localDateTime
        * @return: Date
        */
        public static Date localDateTime2Date(localDateTime dateTime) {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = date.atStartOfDay().atZone(zone).toInstant();
            return Date.from(instant);
       }

       /**
        * fn: getFisrtDay of month about arg-date
        * @param: LocalDateTime
        * @return: Date
        */
       public static Date getFisrtDay(LocalDateTime date) {

           LocalDate firstday = null;
           date.with(TempolarAdjusters.firstDayOfMonth());
           // LocalDate firstday = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
           return TimeUtils.localDateTime2Date(firstday);
       }

        /**
        * fn: getFisrtDay of month about arg-date
        * @param: Date
        * @return: Date
        */
       public static Date getFisrtDay(Date date) {

           logger.error("TIMEDATE " + date.toLocaleString());
           LocalDateTime dateTime = TimeUtils.date2LocalDateTime(date);
           return TimeUtils.getTaxMonth(dateTime);
       }

        /**
        * fn: get 0 clock of the day of time
        * @param: Date
        * @return: LocalDateTime
        */
        public static LocalDateTime getDayStart(LocalDateTime time) {
            return time.withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        }

        /**
        * fn: get lasttime clock of the day of time
        * @param: Date
        * @return: LocalDateTime
        */
        public static LocalDateTime getDayEnd(LocalDateTime time) {
            return time.withHour(23)
                    .withMinute(59)
                    .withSecond(59)
                    .withNano(999999999);
        }
   ```

### basical

- [A]DateFormat: 把日期对象格式化为一个字符串 || 把一个字符串转换为 date 对象。

  ```java
  public void testSimpleDateFormat() throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = new Date();
    System.out.println(dateFormat.format(date));

    //Tue May 15 01:05:53 CST 2018
    String datestr="2018-05-15 01:05:53";
    Date date2 = dateFormat.parse(datestr);
    System.out.println(date2);
  }

  //2018 年 5 月 15 日 下午 1:18
  public void testDateFormat() {
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
    Date date = new Date();
    String datestr = dateFormat.format(date);
    System.out.println(datestr);
  }

  // 日期：
  public static void main(String[] args) {
    // Calendar now = Calendar.getInstance();
    GregorianCalendar now = new GregorianCalendar();
    // Calendar now = new Calendar()出错
    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH);
    int day = now.get(Calendar.DATE);
    int minute = now.get(Calendar.MINUTE);
    int hour = now.get(Calendar.HOUR);
    int second = now.get(Calendar.SECOND);
    System.out.println("当前日期：" + year + "年" + month + "月" + day + "日");
    System.out.println("当前时间：" + hour + "时" + minute + "分" + second + "秒");
  }
  ```
