package std.staffjoy.company.dto.company;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.company.dto.company.CompanyList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ListCompanyResponse extends BaseResponse {

  private CompanyList companyList;
}
