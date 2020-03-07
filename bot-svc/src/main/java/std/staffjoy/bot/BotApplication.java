package std.staffjoy.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"std.staffjoy.mail", "std.staffjoy.sms", "std.staffjoy.company",
    "std.staffjoy.account"})
public class BotApplication {

  public static void main(String[] args) {
    SpringApplication.run(BotApplication.class, args);
  }
}
