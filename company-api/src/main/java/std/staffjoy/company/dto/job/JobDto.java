package std.staffjoy.company.dto.job;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务job
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {

  @NotBlank
  private String id;
  @NotBlank
  private String companyId;
  @NotBlank
  private String teamId;
  @NotBlank
  private String name;
  private boolean archived;
  @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
  @NotBlank
  private String color;
}
