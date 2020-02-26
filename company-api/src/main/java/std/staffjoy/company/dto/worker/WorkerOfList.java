package std.staffjoy.company.dto.worker;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.team.TeamDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerOfList {

  private String userId;
  @Builder.Default
  private List<TeamDto> teams = new ArrayList<TeamDto>();
}
