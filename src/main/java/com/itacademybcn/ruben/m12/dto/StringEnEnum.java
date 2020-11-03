/**
 * 
 */
package com.itacademybcn.ruben.m12.dto;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.Enum;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.ElementType.*;


/**
 * Interfaz Constraint.
 * 
 * @author Rubén
 *
 */

@Documented
@Constraint(validatedBy = StringEnumValidador.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringEnEnum {
	
	  String message() default "PRoblemas con el tipo de empleado. tipo ENUM";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
	  Class<? extends Enum<?>> enumClass();

}                                                      
