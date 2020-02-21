package std.staffjoy.common.validataion;

import java.util.Arrays;
import java.util.TimeZone;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimezoneValidator implements ConstraintValidator<Timezone, String> {

  @Override
  public boolean isValid(String timezone, ConstraintValidatorContext constraintValidatorContext) {
    if (timezone == null) {
      return true;
    }
    return Arrays.asList(TimeZone.getAvailableIDs()).contains(timezone);
  }
}
