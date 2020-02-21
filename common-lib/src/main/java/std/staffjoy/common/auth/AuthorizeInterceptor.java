package std.staffjoy.common.auth;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Authorize authorize = handlerMethod.getMethod().getAnnotation(Authorize.class);
    if (authorize == null) {
      return true;
    }

    String[] allowedHeaders = authorize.value();
    String authorizeHeader = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);
    if (StringUtils.isEmpty(authorizeHeader)) {
      throw new PermissionDeniedException(AuthConstant.ERROR_MSG_MISSING_AUTH_HEADER);
    }

    if (!Arrays.asList(allowedHeaders).contains(authorizeHeader)) {
      throw new PermissionDeniedException(AuthConstant.ERROR_MSG_DO_NOT_HAVE_ACCESS);
    }
    return true;
  }
}
