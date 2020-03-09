package std.staffjoy.account;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import std.staffjoy.common.auth.AuthConstant;

@Configuration
public class TestConfig {

  public static String TEST_USER_ID = UUID.randomUUID().toString();

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        if (!StringUtils.isEmpty(TEST_USER_ID)) {
          template.header(AuthConstant.CURRENT_USER_HEADER, TEST_USER_ID);
        }
      }
    };
  }
}
