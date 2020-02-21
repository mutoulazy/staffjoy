package std.staffjoy.account.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailChangeRequest {

  @NotBlank
  private String userId;
  @NotBlank
  @Email
  private String email;
}
