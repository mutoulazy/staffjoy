package std.staffjoy.common.validataion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

  @Override
  public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
    if (null == phoneField) {
      return true;
    }
    return phoneField != null && phoneField.matches("[0-9]+")
        && (phoneField.length() > 8) && (phoneField.length() < 14);
  }
}
