package std.staffjoy.account.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountList {
  private List<AccountDto> accounts;
  private int limit;
  private int offset;
}
