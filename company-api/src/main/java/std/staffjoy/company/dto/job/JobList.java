package std.staffjoy.company.dto.job;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobList {

  @Builder.Default
  private List<JobDto> jobs = new ArrayList<>();
}

