package std.staffjoy.company.dto.admin;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.directory.DirectoryEntryDto;

/**
 * 公司的管理员列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntries {

  private String companyId;
  @Builder.Default
  private List<DirectoryEntryDto> admins = new ArrayList<DirectoryEntryDto>();
}
