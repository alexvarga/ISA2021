package rs.ac.uns.ftn.isaprojekat.formValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomUnique {


    String message() default "has te be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

/*    Class<? extends FieldValueExists> service();
    String serviceQualifier() default "";
    String fieldName();*/
}
