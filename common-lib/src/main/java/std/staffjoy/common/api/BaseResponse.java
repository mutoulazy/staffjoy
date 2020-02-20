package std.staffjoy.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {

  private String message;
  @Builder.Default
  private ResultCode resultCode = ResultCode.SUCCESS;

  public boolean isSuccess() {
    return resultCode == ResultCode.SUCCESS;
  }
}
