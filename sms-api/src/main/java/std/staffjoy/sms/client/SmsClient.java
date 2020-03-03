package std.staffjoy.sms.client;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.sms.SmsConstant;
import std.staffjoy.sms.dto.SmsRequest;

@FeignClient(name = SmsConstant.SERVICE_NAME, path = "/v1", url = "${staffjoy.sms-service-endpoint}")
public interface SmsClient {

  @PostMapping(path = "/queue_send")
  BaseResponse send(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Valid SmsRequest smsRequest);
}
