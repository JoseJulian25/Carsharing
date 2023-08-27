package com.rd.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AgeValidator.class )
public @interface ValidAge {
    public String message() default "Invalid Date: have to be over 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
