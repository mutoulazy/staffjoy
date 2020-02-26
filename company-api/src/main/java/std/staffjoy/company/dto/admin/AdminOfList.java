package std.staffjoy.company.dto.admin;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.company.CompanyDto;

/**
 * 一个管理员下管理的公司列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminOfList {

  private String userId;
  @Builder.Default
  private List<CompanyDto> companies = new ArrayList<CompanyDto>();
}
