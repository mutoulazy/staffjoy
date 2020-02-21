package std.staffjoy.account.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackEventRequest {
  @NotBlank
  private String userId;
  @NotBlank
  private String event;
}
