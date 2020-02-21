package std.staffjoy.common.validataion;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DayOfWeekValidator implements ConstraintValidator<DayOfWeek, String> {

  private List<String> daysOfWeek =
      Arrays.asList("sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    String inputValue = value.trim().toLowerCase();
    if (daysOfWeek.contains(inputValue)) {
      return true;
    }
    return false;
  }
}
