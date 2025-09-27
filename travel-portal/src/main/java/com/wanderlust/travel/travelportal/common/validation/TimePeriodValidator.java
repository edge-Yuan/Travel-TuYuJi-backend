package com.wanderlust.travel.travelportal.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 时间段验证器
 */
public class TimePeriodValidator implements ConstraintValidator<ValidTimePeriod, String> {
    
    private static final List<String> VALID_TIME_PERIODS = Arrays.asList(
        "早上", "中午", "下午", "晚上"
    );
    
    @Override
    public void initialize(ValidTimePeriod constraintAnnotation) {
        // 初始化方法，可以在这里获取注解参数
    }
    
    @Override
    public boolean isValid(String timePeriod, ConstraintValidatorContext context) {
        // 如果值为null，则认为是有效的（由@NotNull等注解处理）
        if (timePeriod == null) {
            return true;
        }
        
        // 检查是否在有效的时间段列表中
        return VALID_TIME_PERIODS.contains(timePeriod.trim());
    }
}
