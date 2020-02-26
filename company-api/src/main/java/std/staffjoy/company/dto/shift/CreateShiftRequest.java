package std.staffjoy.company.dto.shift;

import java.time.Instant;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.CompanyConstant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShiftRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String teamId;
  @NotNull
  private Instant start;
  @NotNull
  private Instant stop;
  @Builder.Default
  private String userId = "";
  @Builder.Default
  private String jobId = "";
  @NotNull
  private boolean published;

  @AssertTrue(message = "stop must be after start")
  private boolean shopIsAfterStart() {
    long duration = stop.toEpochMilli() - start.toEpochMilli();
    return duration > 0;
  }

  @AssertTrue(message = "Shifts exceed max allowed hour duration")
  private boolean withInMaxDuration() {
    long duration = stop.toEpochMilli() - start.toEpochMilli();
    return duration <= CompanyConstant.MAX_SHIFT_DURATION;
  }
}