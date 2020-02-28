package std.staffjoy.account.controller;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import std.staffjoy.account.service.AccountService;
import std.staffjoy.common.env.EnvConfig;

@RequestMapping("/v1/account")
@RestController
@Validated
public class AccountController {

  final static ILogger logger = SLoggerFactory.getLogger(AccountController.class);

  @Autowired
  private EnvConfig envConfig;

  @Autowired
  private AccountService accountService;
}
