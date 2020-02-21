package std.staffjoy.common.auth;

import lombok.Getter;
import std.staffjoy.common.api.ResultCode;

/**
 * 自定义权限异常类型
 */
public class PermissionDeniedException extends RuntimeException {

  @Getter
  private final ResultCode resultCode;

  public PermissionDeniedException(String message) {
    super(message);
    this.resultCode = ResultCode.UN_AUTHORIZED;
  }

  public PermissionDeniedException(ResultCode resultCode) {
    super(resultCode.getMsg());
    this.resultCode = resultCode;
  }

  public PermissionDeniedException(ResultCode resultCode, Throwable cause) {
    super(cause);
    this.resultCode = resultCode;
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
