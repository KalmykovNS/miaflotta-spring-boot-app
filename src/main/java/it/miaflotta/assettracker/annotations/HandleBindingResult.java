package it.miaflotta.assettracker.annotations;

import it.miaflotta.assettracker.exteptions.InvalidInputException;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {HandleBindingResult.ValidInputValidator.class})
public @interface HandleBindingResult {
    String message() default "Invalid input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidInputValidator implements ConstraintValidator<HandleBindingResult, Object> {

        @Autowired
        private MessageSource messageSource;

        @Override
        public void initialize(HandleBindingResult constraintAnnotation) {
            return;
        }

        @SneakyThrows
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) value;

                if (bindingResult.hasErrors()) {
                    List<String> errorMessages = new LinkedList<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMessages.add(messageSource.getMessage(error, LocaleContextHolder.getLocale()));
                    }
                    throw new InvalidInputException(errorMessages, 400);
                }
            }

            return true;
        }
    }
}