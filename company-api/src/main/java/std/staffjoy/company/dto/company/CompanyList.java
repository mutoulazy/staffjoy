package std.staffjoy.company.dto.company;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyList {

  private List<CompanyDto> companies;
  private int limit;
  private int offset;
}
