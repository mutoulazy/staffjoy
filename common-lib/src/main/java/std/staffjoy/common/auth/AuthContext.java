package std.staffjoy.common.auth;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * A context holder class for holding the current userId and authz info
 */
public class AuthContext {

  private static String getRequestHeader(String headName) {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      String value = request.getHeader(headName);
      return value;
    }
    return null;
  }

  public static String getUserId() {
    return getRequestHeader(AuthConstant.CURRENT_USER_HEADER);
  }

  public static String getAuthz() {
    return getRequestHeader(AuthConstant.AUTHORIZATION_HEADER);
  }

}
