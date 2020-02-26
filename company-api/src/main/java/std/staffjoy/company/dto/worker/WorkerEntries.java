package std.staffjoy.company.dto.worker;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.directory.DirectoryEntryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerEntries {

  private String companyId;
  private String teamId;
  @Builder.Default
  List<DirectoryEntryDto> workers = new ArrayList<>();
}
