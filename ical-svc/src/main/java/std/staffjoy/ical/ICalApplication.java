package std.staffjoy.ical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import std.staffjoy.common.env.StaffjoyWebConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"std.staffjoy.company"})
@Import(value = StaffjoyWebConfig.class)
public class ICalApplication {

  public static void main(String[] args) {
    SpringApplication.run(ICalApplication.class, args);
  }
}
