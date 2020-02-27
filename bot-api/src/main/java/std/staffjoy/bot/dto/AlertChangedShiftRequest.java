package std.staffjoy.bot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.shift.ShiftDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertChangedShiftRequest {

  @NotBlank
  private String userId;
  @NotNull
  private ShiftDto oldShift;
  @NotNull
  private ShiftDto newShift;
}
