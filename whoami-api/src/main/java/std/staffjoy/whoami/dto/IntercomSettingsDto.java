package std.staffjoy.whoami.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntercomSettingsDto {

  private String appId;
  private String userId;
  private String userHash;
  private String name;
  private String email;
  private Instant createdAt;
}
