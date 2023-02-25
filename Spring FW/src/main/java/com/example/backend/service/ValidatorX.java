package com.example.backend.service;

import com.example.backend.validator.ObjectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ObjectValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorX  {
    String message() default "Quanzip definded validator";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
