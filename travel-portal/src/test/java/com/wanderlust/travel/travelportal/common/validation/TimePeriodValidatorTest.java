package com.wanderlust.travel.travelportal.common.validation;

import com.wanderlust.travel.travelportal.common.utils.TimePeriodUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 时间段验证器测试类
 */
@SpringBootTest
public class TimePeriodValidatorTest {
    
    private final TimePeriodValidator validator = new TimePeriodValidator();
    
    @Test
    public void testValidTimePeriods() {
        // 测试有效的时间段
        assertTrue(validator.isValid("早上", null));
        assertTrue(validator.isValid("中午", null));
        assertTrue(validator.isValid("下午", null));
        assertTrue(validator.isValid("晚上", null));
    }
    
    @Test
    public void testInvalidTimePeriods() {
        // 测试无效的时间段
        assertFalse(validator.isValid("凌晨", null));
        assertFalse(validator.isValid("深夜", null));
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }
    
    @Test
    public void testNullTimePeriod() {
        // 测试null值（应该返回true，由@NotNull处理）
        assertTrue(validator.isValid(null, null));
    }
    
    @Test
    public void testTimePeriodUtils() {
        // 测试工具类功能
        List<String> validPeriods = TimePeriodUtils.getAllValidTimePeriods();
        assertEquals(4, validPeriods.size());
        assertTrue(validPeriods.contains("早上"));
        assertTrue(validPeriods.contains("中午"));
        assertTrue(validPeriods.contains("下午"));
        assertTrue(validPeriods.contains("晚上"));
        
        // 测试根据索引获取时间段
        assertEquals("早上", TimePeriodUtils.getTimePeriodByIndex(0));
        assertEquals("中午", TimePeriodUtils.getTimePeriodByIndex(1));
        assertEquals("下午", TimePeriodUtils.getTimePeriodByIndex(2));
        assertEquals("晚上", TimePeriodUtils.getTimePeriodByIndex(3));
        
        // 测试根据天数序号获取时间段
        assertEquals("早上", TimePeriodUtils.getTimePeriodByDaySeq(1));
        assertEquals("中午", TimePeriodUtils.getTimePeriodByDaySeq(2));
        assertEquals("下午", TimePeriodUtils.getTimePeriodByDaySeq(3));
        assertEquals("晚上", TimePeriodUtils.getTimePeriodByDaySeq(4));
        assertEquals("早上", TimePeriodUtils.getTimePeriodByDaySeq(5)); // 循环
        
        // 测试验证功能
        assertTrue(TimePeriodUtils.isValidTimePeriod("早上"));
        assertFalse(TimePeriodUtils.isValidTimePeriod("凌晨"));
        assertFalse(TimePeriodUtils.isValidTimePeriod(null));
    }
}
