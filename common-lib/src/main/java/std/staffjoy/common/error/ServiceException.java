package std.staffjoy.common.error;

import lombok.Getter;
import std.staffjoy.common.api.ResultCode;

/**
 * 自定义服务异常类型
 */
public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = -7470088135017056791L;

  @Getter
  private final ResultCode resultCode;

  public ServiceException(String message) {
    super(message);
    this.resultCode = ResultCode.FAILURE;
  }

  public ServiceException(ResultCode resultCode) {
    super(resultCode.getMsg());
    this.resultCode = resultCode;
  }

  public ServiceException(ResultCode resultCode, String msg) {
    super(msg);
    this.resultCode = resultCode;
  }

  public ServiceException(ResultCode resultCode, Throwable cause) {
    super(cause);
    this.resultCode = resultCode;
  }

  public ServiceException(String msg, Throwable cause) {
    super(msg, cause);
    this.resultCode = ResultCode.FAILURE;
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }

  public Throwable doFillInStackTrace() {
    return super.fillInStackTrace();
  }
}
