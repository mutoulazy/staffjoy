package std.staffjoy.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"std.staffjoy.account", "std.staffjoy.bot"})
@SpringBootApplication
public class CompanyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CompanyApplication.class, args);
  }
}
