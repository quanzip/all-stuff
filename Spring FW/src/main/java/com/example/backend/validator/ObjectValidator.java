package com.example.backend.validator;

import com.example.backend.service.ValidatorX;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ObjectValidator implements ConstraintValidator<ValidatorX, Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(integer))
            System.out.println("Legs can not be null");
        else if(integer % 2 != 0 || integer > 4)
            System.out.println("Legs must be even");
        else  return true;
        return false;
    }
}
