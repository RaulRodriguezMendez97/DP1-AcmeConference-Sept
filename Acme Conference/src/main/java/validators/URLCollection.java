
package validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({
	ElementType.METHOD, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlCollectionValidator.class)
public @interface URLCollection {

	String message() default "{org.hibernate.validator.constraints.URL.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
