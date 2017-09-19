package com.hhx.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameConstraintValidator.class)
public @interface UsernameValidtor {

    String message() default "存在非法字符";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
