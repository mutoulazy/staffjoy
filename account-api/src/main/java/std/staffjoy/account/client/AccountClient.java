package std.staffjoy.account.client;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import std.staffjoy.account.AccountConstant;
import std.staffjoy.account.dto.CreateAccountRequest;
import std.staffjoy.account.dto.GenericAccountResponse;

@FeignClient(name = AccountConstant.SERVICE_NAME, path = "/v1/account", url = "${account-service-endpoint}")
public interface AccountClient {

  @PostMapping(path = "/create")
  GenericAccountResponse createAccount(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid CreateAccountRequest request
  );
}
