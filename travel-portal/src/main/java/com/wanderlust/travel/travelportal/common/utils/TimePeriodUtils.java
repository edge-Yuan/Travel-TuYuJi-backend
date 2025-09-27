package com.wanderlust.travel.travelportal.common.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 时间段工具类
 */
public class TimePeriodUtils {
    
    /**
     * 有效的时间段列表
     */
    public static final List<String> VALID_TIME_PERIODS = Arrays.asList(
        "早上", "中午", "下午", "晚上"
    );
    
    /**
     * 根据索引获取时间段
     * @param index 索引（0-3）
     * @return 时间段
     */
    public static String getTimePeriodByIndex(int index) {
        if (index >= 0 && index < VALID_TIME_PERIODS.size()) {
            return VALID_TIME_PERIODS.get(index);
        }
        return "早上"; // 默认值
    }
    
    /**
     * 根据天数序号获取时间段
     * @param daySeq 天数序号
     * @return 时间段
     */
    public static String getTimePeriodByDaySeq(int daySeq) {
        return getTimePeriodByIndex((daySeq - 1) % VALID_TIME_PERIODS.size());
    }
    
    /**
     * 验证时间段是否有效
     * @param timePeriod 时间段
     * @return 是否有效
     */
    public static boolean isValidTimePeriod(String timePeriod) {
        return timePeriod != null && VALID_TIME_PERIODS.contains(timePeriod.trim());
    }
    
    /**
     * 获取所有有效时间段
     * @return 时间段列表
     */
    public static List<String> getAllValidTimePeriods() {
        return VALID_TIME_PERIODS;
    }
}
