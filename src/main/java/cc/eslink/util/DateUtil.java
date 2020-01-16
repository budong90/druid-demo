package cc.eslink.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

/**
 * 日期处理工具类
 * @author van.zheng
 *
 */
public class DateUtil {

	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	private static final Object object = new Object();

	/**
	 * 时间相减得到天数
	 * @throws ParseException 
	 */
	public static long getDaySub(Date beginDate, Date endDate) {
		long day = 0;
		String beginDateStr = DateUtil.DateToString(beginDate, DateStyle.YYYY_MM_DD);
		String endDateStr = DateUtil.DateToString(endDate, DateStyle.YYYY_MM_DD);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
		
		try {
			day = (format.parse(endDateStr).getTime() - format.parse(beginDateStr).getTime())
					/ (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}

    /**
     * 相隔秒数
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getSecondSub(Date beginDate, Date endDate) {
        long second = 0;
        String beginDateStr = DateUtil.DateToString( beginDate, DateStyle.YYYY_MM_DD_HH_MM_SS );
        String endDateStr = DateUtil.DateToString( endDate, DateStyle.YYYY_MM_DD_HH_MM_SS );

        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        try {
            second = (format.parse( endDateStr ).getTime() - format.parse( beginDateStr ).getTime()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return second;
    }

    public static int getMonthSubWithNow(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM" );
        String afferentYearMonth = StringToString( dateStr, DateStyle.YYYY_MM );
        String nowYearMonth = YearMonth.now().toString();
        Calendar afferent = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        try {
            afferent.setTime( sdf.parse( afferentYearMonth ) );
            now.setTime( sdf.parse( nowYearMonth ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = (now.get( Calendar.YEAR ) - afferent.get( Calendar.YEAR )) * 12;
        int month = now.get( Calendar.MONTH ) - afferent.get( Calendar.MONTH );
        return Math.abs( year + month );
    }

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 *            日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException
	 *             异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 * 
	 * @param date
	 *            日期
	 * @param dateType
	 *            日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		int num = 0;
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			num = calendar.get(dateType);
		}
		return num;
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param dateType
	 *            类型
	 * @param amount
	 *            数值
	 * @return 计算后日期字符串
	 */
	private static String addInteger(String date, int dateType, int amount) {
		String dateString = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			myDate = addInteger(myDate, dateType, amount);
			dateString = DateToString(myDate, dateStyle);
		}
		return dateString;
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * 
	 * @param date
	 *            日期
	 * @param dateType
	 *            类型
	 * @param amount
	 *            数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 获取精确的日期
	 * 
	 * @param timestamps
	 *            时间long集合
	 * @return 日期
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i)
								- timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i),
								timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
				// 因此不能将minAbsoluteValue取默认值0
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					minAbsoluteValue = absoluteValues.get(0);
					for (int i = 1; i < absoluteValues.size(); i++) {
						if (minAbsoluteValue > absoluteValues.get(i)) {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);

					long dateOne = timestampsLastTmp[0];
					long dateTwo = timestampsLastTmp[1];
					if (absoluteValues.size() > 1) {
						timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne
								: dateTwo;
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/**
	 * 判断字符串是否为日期字符串
	 * 
	 * @param date
	 *            日期字符串
	 * @return true or false
	 */
	public static boolean isDate(String date) {
		boolean isDate = false;
		if (date != null) {
			if (getDateStyle(date) != null) {
				isDate = true;
			}
		}
		return isDate;
	}

	/**
	 * 获取日期字符串的日期风格。失敗返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 日期风格
	 */
	public static DateStyle getDateStyle(String date) {
		DateStyle dateStyle = null;
		Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DateStyle style : DateStyle.values()) {
			if (style.isShowOnly()) {
				continue;
			}
			Date dateTmp = null;
			if (date != null) {
				try {
					ParsePosition pos = new ParsePosition(0);
					dateTmp = getDateFormat(style.getValue()).parse(date, pos);
					if (pos.getIndex() != date.length()) {
						dateTmp = null;
					}
				} catch (Exception e) {
				}
			}
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), style);
			}
		}
		Date accurateDate = getAccurateDate(timestamps);
		if (accurateDate != null) {
			dateStyle = map.get(accurateDate.getTime());
		}
		return dateStyle;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 日期
	 */
	public static Date StringToDate(String date) {
		DateStyle dateStyle = getDateStyle(date);
		return StringToDate(date, dateStyle);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 日期
	 */
	public static Date StringToDate(String date, String pattern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(pattern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param dateStyle
	 *            日期风格
	 * @return 日期
	 */
	public static Date StringToDate(String date, DateStyle dateStyle) {
		Date myDate = null;
		if (dateStyle != null) {
			myDate = StringToDate(date, dateStyle.getValue());
		}
		return myDate;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param dateStyle
	 *            日期风格
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (dateStyle != null) {
			dateString = DateToString(date, dateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param newPattern
	 *            新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String newPattern) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newPattern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param newDateStyle
	 *            新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle newDateStyle) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newDateStyle);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param olddPattern
	 *            旧日期格式
	 * @param newPattern
	 *            新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddPattern,
			String newPattern) {
		return DateToString(StringToDate(date, olddPattern), newPattern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param olddDteStyle
	 *            旧日期风格
	 * @param newParttern
	 *            新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle olddDteStyle,
			String newParttern) {
		String dateString = null;
		if (olddDteStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(),
					newParttern);
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param olddPattern
	 *            旧日期格式
	 * @param newDateStyle
	 *            新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddPattern,
			DateStyle newDateStyle) {
		String dateString = null;
		if (newDateStyle != null) {
			dateString = StringToString(date, olddPattern,
					newDateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * 
	 * @param date
	 *            旧日期字符串
	 * @param olddDteStyle
	 *            旧日期风格
	 * @param newDateStyle
	 *            新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle olddDteStyle,
			DateStyle newDateStyle) {
		String dateString = null;
		if (olddDteStyle != null && newDateStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(),
					newDateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param yearAmount
	 *            增加数量。可为负数
	 * @return 增加年份后的日期字符串
	 */
	public static String addYear(String date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param yearAmount
	 *            增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param monthAmount
	 *            增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param monthAmount
	 *            增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param dayAmount
	 *            增加数量。可为负数
	 * @return 增加天数后的日期字符串
	 */
	public static String addDay(String date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param dayAmount
	 *            增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param hourAmount
	 *            增加数量。可为负数
	 * @return 增加小时后的日期字符串
	 */
	public static String addHour(String date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param hourAmount
	 *            增加数量。可为负数
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param minuteAmount
	 *            增加数量。可为负数
	 * @return 增加分钟后的日期字符串
	 */
	public static String addMinute(String date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param dayAmount
	 *            增加数量。可为负数
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @param dayAmount
	 *            增加数量。可为负数
	 * @return 增加秒钟后的日期字符串
	 */
	public static String addSecond(String date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param dayAmount
	 *            增加数量。可为负数
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 年份
	 */
	public static int getYear(String date) {
		return getYear(StringToDate(date));
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 月份
	 */
	public static int getMonth(String date) {
		return getMonth(StringToDate(date));
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 天
	 */
	public static int getDay(String date) {
		return getDay(StringToDate(date));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 小时
	 */
	public static int getHour(String date) {
		return getHour(StringToDate(date));
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 分钟
	 */
	public static int getMinute(String date) {
		return getMinute(StringToDate(date));
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 秒钟
	 */
	public static int getSecond(String date) {
		return getSecond(StringToDate(date));
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * 
	 * @param date
	 *            日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 日期
	 */
	public static String getDate(String date) {
		return StringToString(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认格式yyyyMMddHHmmssSSS。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @return 日期
	 */
	public static String getLongDateSSS(Date date) {
		return DateToString(date, DateStyle.YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 获取时间。默认格式yyyy-MM-dd HH:mm:ss。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @return 日期
	 */
	public static String getLongDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * 
	 * @param date
	 *            日期字符串
	 * @return 时间
	 */
	public static String getTime(String date) {
		return StringToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @return 时间
	 */
	public static String getTime(Date date) {
		return DateToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param date
	 *            日期字符串
	 * @param otherDate
	 *            另一个日期字符串
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date), StringToDate(otherDate));
	}
	/**
	 * @param date
	 *            日期
	 * @param otherDate
	 *            另一个日期
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		int num = -1;
		Date dateTmp = DateUtil.StringToDate(DateUtil.getDate(date),
				DateStyle.YYYY_MM_DD);
		Date otherDateTmp = DateUtil.StringToDate(DateUtil.getDate(otherDate),
				DateStyle.YYYY_MM_DD);
		if (dateTmp != null && otherDateTmp != null) {
			long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}
	
    /**
     * 
     * @Title: compareDate
     * @Description: TODO(时间比较大小.相等返回0，date大于返回1，小于otherDate返回-1.)
     * @param @param
     *            date
     * @param @param
     *            otherDate
     * @param @return
     * 
     * @return int
     */
	public static int compareDate(String date, String otherDate){
		return compareDate(StringToDate(date), StringToDate(otherDate));
	}

	public static int compareDate2(String date, String otherDate){
		if (StringUtils.isBlank(date) && StringUtils.isBlank(otherDate)) {
			return 0;
		}
		if (StringUtils.isBlank(date)) {
			return -1;
		}
		if (StringUtils.isBlank(otherDate)) {
			return 1;
		}
		return compareDate(StringToDate(date), StringToDate(otherDate));
	}

	/**
	 * 
	 * @Title: compareDate 
	 * @Description: TODO(时间比较大小) 
	 * @param @param date
	 * @param @param otherDate
	 * @param @return    
	 * @return int
	 */
	public static int compareDate(Date date, Date otherDate){
		int num = -1;
		if (date != null && otherDate != null) {
			num=date.compareTo(otherDate);
		}
		return num;
	}

    public static String getDayMinString(String date) {
        return DateToString( getDayMin( date ), DateStyle.YYYY_MM_DD_HH_MM_SS__SSS );
    }
    public static Date getDayMin(String date) {
        return getDayMin( StringToDate( date ) );
    }
	/**
	 * 获取日期的最小时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayMin(Date date) {
	    if (date == null) {
            return null;
        }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

    public static String getDayMaxString(String date) {
        return DateToString( getDayMax( date ), DateStyle.YYYY_MM_DD_HH_MM_SS__SSS );
    }
    public static Date getDayMax(String date){
        return getDayMax( StringToDate( date ) );
	}
    
    public static String getDayMaxStr(String date) {

        return DateToString( getDayMax( StringToDate( date ) ), DateStyle.YYYY_MM_DD_HH_MM_SS );
    }

    public static String getDayMinStr(String date) {

        return DateToString( getDayMin( StringToDate( date ) ), DateStyle.YYYY_MM_DD_HH_MM_SS );
    }
    
	/**
	 * 获取日期的最大时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayMax(Date date) {
	    if (date == null) {
            return null;
        }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}
	
	/**
	 * 获取当前年度
	 * @return
	 */
	public static int getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get( Calendar.MONTH );
        return month + 1;
    }
	/**
	 * 获取指定往前天数日期
	 * @return
	 */
	public static String getPreviousDate(int num){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -num);
		Date date = calendar.getTime();
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}
	/**
	 * 
	 * @Title: getCurrentDate 
	 * @Description: TODO(获取当前日期) 
	 * @param @return    
	 * @return String
	 */
	public static String getCurrentDate(){
		
		return DateToString(new Date(), DateStyle.YYYY_MM_DD);
	}

	public static String getCurrentDate2(){
		return DateToString(new Date(), DateStyle.YYYYMMDD);
	}
	
	/**
	 * 获取当前日期时间(精确到毫秒)
	 * @return
	 */
	public static String getCurrentDateTime(){
		return DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 获取两个时间的相差值(精确到毫秒)
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String getConsumeTime(String beginDate, String endDate) {
		// 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
		if (beginDate.equals(endDate)) {
			return "0毫秒";
		}

		SimpleDateFormat dfs = new SimpleDateFormat(
				DateStyle.YYYY_MM_DD_HH_MM_SS_SSS.getValue());
		long between = 0;
		try {
			Date begin = dfs.parse(beginDate);
			Date end = dfs.parse(endDate);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
				- min * 60 * 1000 - s * 1000);

		String consumeTime = "";
		if (day > 0) {
			consumeTime += day + "天";
		}
		if (hour > 0) {
			consumeTime += hour + "小时";
		}
		if (min > 0) {
			consumeTime += min + "分";
		}
		if (s > 0) {
			consumeTime += s + "秒";
		}
		if (ms > 0) {
			consumeTime += ms + "毫秒";
		}
		return consumeTime;
	}
	  
	/** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
	 public static Date getYearFirst(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        Date currYearFirst = calendar.getTime();  
	        return currYearFirst;  
	  }  
	      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return String 
     */  
    public static String getYearFirstStr(int year){
    	return DateToString(getYearFirst(year),DateStyle.YYYY_MM_DD);
    }
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return String 
     */ 
    public static String getYearLastStr(int year){
    	return DateToString(getYearLast(year),DateStyle.YYYY_MM_DD);
    }
    /**
     * 
     * TODO 是否在近24小时内
     * 
     * @param time
     * @return
     */
    public static boolean isInLast24Hour(String time) { 
        return isInLast24Hour( StringToDate( time ,DateStyle.YYYY_MM_DD_HH_MM_SS) );
        
    } 
    /**
     * 
     * TODO 是否在近24小时内
     * 
     * @param time
     * @return
     */
    public static boolean isInLast24Hour(Date time) { 
        if(time==null){
            return false;
        }
        double rule = 24;
//        if (ConfigReader.isSystemTest()) {
//            rule = 0.1;
//        }
        Date now = new Date();
        long cha = now.getTime() - time.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);

        if (result <= rule) {
             return true; 
        }else{  
             return false; 
        } 
    }

    /**
     * 
     * TODO 间隔天数对比
     * 
     * @param time
     * @param itervalDay
     * @return
     */
    public static boolean isInLastItervalDay(Date time, Integer itervalDay) {
        if (time == null || itervalDay == null) {
            return false;
        }
        double rule = 24;
//        if (ConfigReader.isSystemTest()) {
//            rule = 0.1;
//        }
        Date now = new Date();
        long cha = now.getTime() - time.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        double itervalTime = rule * itervalDay;
        if (result <= itervalTime) {
            return true;
        }
        else {
            return false;
        }
    }

	/**
	 * 计算两个时间之间的相差分钟数
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Long getConsumeTimeOfMinute(String beginDate, String endDate) {
		// 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
		if (beginDate.equals(endDate)) {
			return 0L;
		}

		SimpleDateFormat dfs = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
		long diff = 0;
		try {
			Date begin = dfs.parse(beginDate);
			Date end = dfs.parse(endDate);
			long beginTime = begin.getTime();
			long endTime = end.getTime();
			if(beginTime < endTime) {
				diff = endTime - beginTime;
			} else {
				diff = beginTime - endTime;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		long minutes = diff / (60 * 1000);

		return minutes;
	}

    /**
     * 一天的开始时间 yyyy:MM:dd 00:00:00
     * 
     * @param date
     * @return
     */
    public static String getStartTime(String date) {
        return getStartTime( StringToDate( date ) );
    }

    public static String getStartTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String startStr = simpleDateFormat.format( dayStart );
        return startStr;
    }

    /**
     * 一天的结束时间 yyyy:MM:dd 23：59：59
     * 
     * @param date
     * @return
     */
    public static String getEndTime(String date) {
        return getEndTime( StringToDate( date ) );
    }

    public static String getEndTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.set( Calendar.HOUR_OF_DAY, 23 );
        calendar.set( Calendar.MINUTE, 59 );
        calendar.set( Calendar.SECOND, 59 );
        calendar.set( Calendar.MILLISECOND, 999 );
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String startStr = simpleDateFormat.format( dayStart );
        return startStr;
    }

	/**
	 * 是否是当前年
	 *
	 * @param date
	 * @return
	 */
	public static boolean isCurrentYear(String date) {
		if (Objects.isNull( date ) || Objects.equals( date, "" )) {
			return false;
		}
		int year = getYear( date );
		return Objects.deepEquals( year, getCurrentYear() );

	}

    public static void main(String[] args) {
        System.out.println( getMonthSubWithNow( "2019-05-30 10:15:03" ) );
    }

	public static String addDay(String curDate, int dayAmount, String limitDate) {
		String offsetDate = addDay(curDate, dayAmount);
		if (dayAmount < 0 && DateUtil.compareDate(offsetDate, limitDate) < 0) {
			return limitDate;
		} else if (dayAmount > 0 && DateUtil.compareDate(offsetDate, limitDate) > 0) {
			return limitDate;
		}
		return offsetDate;
	}

    public static Date getFisrtDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, year );
        cal.set( Calendar.MONTH, month - 1 );
        int firstDay = cal.getActualMinimum( Calendar.DAY_OF_MONTH );
        cal.set( Calendar.DAY_OF_MONTH, firstDay );
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, year );
        cal.set( Calendar.MONTH, month - 1 );
        int lastDay = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
        cal.set( Calendar.DAY_OF_MONTH, lastDay );
        return cal.getTime();
    }
}