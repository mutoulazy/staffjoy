package std.staffjoy.company.dto.job;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateJobRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String teamId;
  @NotBlank
  private String name;
  @NotBlank
  private String color;
}
