package com.rd.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;


public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateBirth, ConstraintValidatorContext constraintValidatorContext) {
        if(dateBirth == null){
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dateBirth, currentDate).getYears();
        return age >= 18;
    }
}
