package std.staffjoy.sms.controller;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.api.ResultCode;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.auth.Authorize;
import std.staffjoy.sms.dto.SmsRequest;
import std.staffjoy.sms.props.AppProps;
import std.staffjoy.sms.service.SmsSendService;

@RestController
@RequestMapping("/v1")
@Validated
public class SmsController {

  static final ILogger logger = SLoggerFactory.getLogger(SmsController.class);

  @Autowired
  private AppProps appProps;

  @Autowired
  private SmsSendService smsSendService;

  @PostMapping(path = "/queue_send")
  @Authorize({
      AuthConstant.AUTHORIZATION_COMPANY_SERVICE,
      AuthConstant.AUTHORIZATION_ACCOUNT_SERVICE,
      AuthConstant.AUTHORIZATION_BOT_SERVICE
  })
  public BaseResponse send(@RequestBody @Valid SmsRequest smsRequest) {

    if (appProps.isWhiteListOnly()) {
      String whiteList = appProps.getWhiteListPhoneNumbers();
      boolean allowedToSend = !StringUtils.isEmpty(whiteList)
          && whiteList.contains(smsRequest.getTo());
      if (!allowedToSend) {
        String msg = String
            .format("prevented sending to number %s due to whitelist", smsRequest.getTo());
        logger.warn(msg);
        return BaseResponse.builder().resultCode(ResultCode.REQ_REJECT).message(msg).build();
      }
    }

    smsSendService.sendSmsAsync(smsRequest);
    String msg = String.format("sent message to %s. async", smsRequest.getTo());
    logger.debug(msg);
    return BaseResponse.builder().message(msg).build();
  }

}