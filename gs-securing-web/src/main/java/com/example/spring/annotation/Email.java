package com.example.spring.annotation;

import com.example.spring.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {

    String message() default "Invalid email-address provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
