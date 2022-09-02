package jdi.springboot.springmvc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class InEnumValidator implements ConstraintValidator<InEnum, Integer> {

  private Set<Integer> values;

  @Override
  public void initialize(InEnum annotation) {
    IntArrayValuable[] values = annotation.value().getEnumConstants();
    if (values.length == 0) {
      this.values = Collections.emptySet();
    } else {
      this.values = Arrays.stream(values[0].array()).boxed().collect(Collectors.toSet());
    }
  }

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    if (values.contains(value)) {
      return true;
    }
    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(
            context
                .getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}}", values.toString()))
        .addConstraintViolation();
    return false;
  }
}
