package std.staffjoy.company.dto.company;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.company.dto.company.CompanyDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericCompanyResponse extends BaseResponse {

  private CompanyDto company;
}
