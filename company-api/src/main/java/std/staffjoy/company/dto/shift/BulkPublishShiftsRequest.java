package std.staffjoy.company.dto.shift;

import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 批量发布班次请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkPublishShiftsRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String teamId;
  private String userId;
  private String jobId;
  @NotNull
  private Instant shiftStartAfter;
  @NotNull
  private Instant shiftStartBefore;
  private boolean published;
}
