package std.staffjoy.company.dto;

import java.time.Instant;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerShiftListRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String teamId;
  @NotBlank
  private String workerId;
  @NotNull
  private Instant shiftStartAfter;
  @NotNull
  private Instant shiftStartBefore;

  @AssertTrue(message = "shift_start_after must be before shift_start_before")
  private boolean correctAfterAndBefore() {
    long duration = shiftStartAfter.toEpochMilli() - shiftStartBefore.toEpochMilli();
    return duration < 0;
  }
}
