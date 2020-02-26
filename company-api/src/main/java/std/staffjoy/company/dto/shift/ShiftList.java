package std.staffjoy.company.dto.shift;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.shift.ShiftDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftList {

  @Builder.Default
  private List<ShiftDto> shifts = new ArrayList<>();
  private Instant shiftStartAfter;
  private Instant shiftStartBefore;
}
