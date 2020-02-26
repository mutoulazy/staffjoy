package std.staffjoy.company.dto.team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.common.validataion.DayOfWeek;
import std.staffjoy.common.validataion.Timezone;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTeamRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String name;
  @Timezone
  private String timezone;
  @DayOfWeek
  private String dayWeekStarts;
  @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
  @NotEmpty
  private String color;
}
