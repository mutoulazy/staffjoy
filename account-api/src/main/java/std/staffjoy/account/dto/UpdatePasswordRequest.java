package std.staffjoy.account.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class UpdatePasswordRequest {
  @NotBlank
  private String userId;
  @NotBlank
  @Size(min = 6)
  private String password;
}
