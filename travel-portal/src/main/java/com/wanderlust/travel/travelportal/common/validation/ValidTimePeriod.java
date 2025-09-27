package com.wanderlust.travel.travelportal.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * 时间段验证注解
 */
@Documented
@Constraint(validatedBy = TimePeriodValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimePeriod {
    
    String message() default "时间段只能是：早上、中午、下午、晚上";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
