package std.staffjoy.bot.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.shift.ShiftDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertRemovedShiftsRequest {

  @NotBlank
  private String userId;
  @Builder.Default
  private List<ShiftDto> oldShifts = new ArrayList<>();
}