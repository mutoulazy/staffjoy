package std.staffjoy.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.api.ResultCode;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.auth.AuthContext;
import std.staffjoy.common.auth.Authorize;
import std.staffjoy.common.auth.PermissionDeniedException;
import std.staffjoy.common.error.ServiceException;
import std.staffjoy.company.dto.admin.AdminEntries;
import std.staffjoy.company.dto.admin.AdminOfList;
import std.staffjoy.company.dto.admin.GetAdminOfResponse;
import std.staffjoy.company.dto.admin.ListAdminResponse;
import std.staffjoy.company.dto.directory.DirectoryEntryDto;
import std.staffjoy.company.dto.directory.DirectoryEntryRequest;
import std.staffjoy.company.dto.directory.GenericDirectoryResponse;
import std.staffjoy.company.service.AdminService;
import std.staffjoy.company.service.PermissionService;

@RestController
@RequestMapping("/v1/company/admin")
@Validated
public class AdminController {

  @Autowired
  AdminService adminService;

  @Autowired
  PermissionService permissionService;

  @GetMapping(path = "/list")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public ListAdminResponse listAdmins(@RequestParam String companyId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(companyId);
    }
    AdminEntries adminEntries = adminService.listAdmins(companyId);
    return new ListAdminResponse(adminEntries);
  }

  @GetMapping(path = "/get")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_WWW_SERVICE
  })
  public GenericDirectoryResponse getAdmin(@RequestParam String companyId,
      @RequestParam String userId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(companyId);
    }
    DirectoryEntryDto directoryEntryDto = adminService.getAdmin(companyId, userId);
    if (directoryEntryDto == null) {
      throw new ServiceException(ResultCode.NOT_FOUND, "admin relationship not found");
    }
    return new GenericDirectoryResponse(directoryEntryDto);
  }

  @PostMapping(path = "/create")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_WWW_SERVICE
  })
  public GenericDirectoryResponse createAdmin(
      @RequestBody @Validated DirectoryEntryRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(request.getCompanyId());
    }
    DirectoryEntryDto directoryEntryDto = adminService
        .createAdmin(request.getCompanyId(), request.getUserId());
    return new GenericDirectoryResponse(directoryEntryDto);
  }

  @DeleteMapping(path = "/delete")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public BaseResponse deleteAdmin(@RequestBody @Validated DirectoryEntryRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(request.getCompanyId());
    }
    adminService.deleteAdmin(request.getCompanyId(), request.getUserId());
    return BaseResponse.builder().build();
  }

  @GetMapping(path = "/admin_of")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_ACCOUNT_SERVICE,
      AuthConstant.AUTHORIZATION_WHOAMI_SERVICE,
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_WWW_SERVICE
  })
  public GetAdminOfResponse getAdminOf(@RequestParam String userId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      if (!userId.equals(AuthContext.getUserId())) {
        throw new PermissionDeniedException("You do not have access to this service");
      }
    }
    AdminOfList adminOfList = adminService.getAdminOf(userId);
    return new GetAdminOfResponse(adminOfList);
  }
}
