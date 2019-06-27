package group.flowbird.mediationservice.validation;

import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

public interface ValidationGroup {

    interface Create {}

    interface Update {}

    interface Evaluate{}

    @ConstraintComposition(OR)
    @Null
    @NotBlank
    @ReportAsSingleViolation
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Constraint(validatedBy = { })
    @interface NullOrNotBlank {
        String message() default "{org.hibernate.validator.constraints.NullOrNotBlank.message}";
        Class<?>[] groups() default { };
        Class<? extends Payload>[] payload() default { };
    }
}
