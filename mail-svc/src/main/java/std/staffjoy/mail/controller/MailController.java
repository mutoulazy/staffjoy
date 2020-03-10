package std.staffjoy.mail.controller;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.mail.dto.EmailRequest;
import std.staffjoy.mail.service.MailSendService;

@RestController
@RequestMapping("/v1")
@Validated
public class MailController {

  private static ILogger logger = SLoggerFactory.getLogger(MailController.class);

  @Autowired
  private MailSendService mailSendService;

  @PostMapping(path = "/send")
  public BaseResponse send(@RequestBody @Valid EmailRequest request) {
    mailSendService.sendMailAsync(request);
    return BaseResponse.builder().message("email has been sent async.").build();
  }
}
