package std.staffjoy.company.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthGraphResponse {

  private Map<String, Integer> peopleScheduledPerWeek;
  private Integer peopleOnShift;
}
