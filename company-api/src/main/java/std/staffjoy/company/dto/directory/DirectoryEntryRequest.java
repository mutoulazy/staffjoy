package std.staffjoy.company.dto.directory;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectoryEntryRequest {

  @NotBlank
  private String companyId;
  @NotBlank
  private String userId;
}
