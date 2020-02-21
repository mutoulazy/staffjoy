package std.staffjoy.account.client;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import std.staffjoy.account.AccountConstant;
import std.staffjoy.account.dto.AccountDto;
import std.staffjoy.account.dto.CreateAccountRequest;
import std.staffjoy.account.dto.EmailChangeRequest;
import std.staffjoy.account.dto.EmailConfirmation;
import std.staffjoy.account.dto.GenericAccountResponse;
import std.staffjoy.account.dto.GetOrCreateRequest;
import std.staffjoy.account.dto.ListAccountResponse;
import std.staffjoy.account.dto.PasswordResetRequest;
import std.staffjoy.account.dto.SyncUserRequest;
import std.staffjoy.account.dto.TrackEventRequest;
import std.staffjoy.account.dto.UpdatePasswordRequest;
import std.staffjoy.account.dto.VerifyPasswordRequest;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.validataion.PhoneNumber;

@FeignClient(name = AccountConstant.SERVICE_NAME, path = "/v1/account", url = "${staffjoy.account-service-endpoint}")
public interface AccountClient {

  @PostMapping(path = "/create")
  GenericAccountResponse createAccount(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid CreateAccountRequest request
  );

  @PostMapping("/get_or_create")
  GenericAccountResponse getOrCreateAccount(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid GetOrCreateRequest request
  );

  @GetMapping("/list")
  ListAccountResponse listAccounts(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestParam int offset,
      @RequestParam @Min(0) int limit
  );

  @GetMapping("/get")
  GenericAccountResponse getAccount(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestParam @NotBlank String userId
  );

  @GetMapping("/get_account_by_phonenumber")
  GenericAccountResponse getAccountByPhonenumber(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestParam @PhoneNumber String phoneNumber
  );

  @PutMapping("/update")
  GenericAccountResponse updateAccount(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid AccountDto account
  );

  @PutMapping("/update_password")
  BaseResponse updatePassword(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid UpdatePasswordRequest request
  );

  @PostMapping("/verify_password")
  GenericAccountResponse verifyPassword(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid VerifyPasswordRequest request
  );

  @PostMapping("/request_password_reset")
  BaseResponse requestPasswordReset(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid PasswordResetRequest request
  );

  @PostMapping("/request_email_change")
  BaseResponse requestEmailChange(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid EmailChangeRequest request
  );

  @PostMapping("/change_email")
  BaseResponse changeEmail(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String auth,
      @RequestBody @Valid EmailConfirmation request
  );

  @PostMapping("/track_event")
  BaseResponse trackEvent(@RequestBody @Valid TrackEventRequest request);

  @PostMapping("/sync_user")
  BaseResponse syncUser(@RequestBody @Valid SyncUserRequest request);
}
