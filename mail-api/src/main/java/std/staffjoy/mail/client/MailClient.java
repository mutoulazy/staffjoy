package std.staffjoy.mail.client;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.mail.MailConstant;
import std.staffjoy.mail.dto.EmailRequest;

@FeignClient(name = MailConstant.SERVICE_NAME, path = "/v1", url = "${staffjoy.email-service-endpoint}")
public interface MailClient {

  @PostMapping(path = "/send")
  BaseResponse send(@RequestBody @Valid EmailRequest request);
}
