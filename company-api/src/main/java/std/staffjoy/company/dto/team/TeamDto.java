package std.staffjoy.company.dto.team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.common.validataion.DayOfWeek;
import std.staffjoy.common.validataion.Timezone;

/**
 * 工作小组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {

  @NotBlank
  private String id;
  @NotBlank
  private String companyId;
  @NotBlank
  private String name;
  private boolean archived;
  @Timezone
  @NotBlank
  private String timezone;
  @DayOfWeek
  @NotBlank
  private String dayWeekStarts;
  @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
  @NotBlank
  private String color;
}
