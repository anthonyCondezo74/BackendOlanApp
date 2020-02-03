package com.olan.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = RazonSocialValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RazonSocial {
	String message() default "Razón social, nombres ó apellidos incorrectos";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
