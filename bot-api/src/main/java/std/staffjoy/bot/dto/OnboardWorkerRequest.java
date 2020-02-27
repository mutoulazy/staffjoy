package std.staffjoy.bot.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnboardWorkerRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String userId;
}
