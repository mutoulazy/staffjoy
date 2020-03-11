package std.staffjoy.whoami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import std.staffjoy.common.env.StaffjoyRestConfig;

@Import(value = StaffjoyRestConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"std.staffjoy.company", "std.staffjoy.account"})
public class WhoAmIApplication {
  public static void main(String[] args) {
    SpringApplication.run(WhoAmIApplication.class, args);
  }
}
