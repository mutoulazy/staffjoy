package std.staffjoy.company.dto.directory;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.directory.DirectoryEntryDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectoryList {

  @Builder.Default
  private List<DirectoryEntryDto> accounts = new ArrayList<DirectoryEntryDto>();
  private int limit;
  private int offset;
}
