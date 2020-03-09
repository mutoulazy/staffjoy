package std.staffjoy.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.auth.AuthContext;
import std.staffjoy.common.auth.Authorize;
import std.staffjoy.common.validataion.Group1;
import std.staffjoy.common.validataion.Group2;
import std.staffjoy.company.dto.company.CompanyDto;
import std.staffjoy.company.dto.company.CompanyList;
import std.staffjoy.company.dto.company.GenericCompanyResponse;
import std.staffjoy.company.dto.company.ListCompanyResponse;
import std.staffjoy.company.service.CompanyService;
import std.staffjoy.company.service.PermissionService;

@RestController
@RequestMapping("/v1/company")
@Validated
public class CompanyController {

  @Autowired
  CompanyService companyService;

  @Autowired
  PermissionService permissionService;

  @PostMapping(path = "/create")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_WWW_SERVICE
  })
  public GenericCompanyResponse createCompany(
      @RequestBody @Validated({Group2.class}) CompanyDto companyDto) {
    CompanyDto newCompanyDto = companyService.createCompany(companyDto);
    return new GenericCompanyResponse(newCompanyDto);
  }

  @GetMapping(path = "/list")
  @Authorize(value = {AuthConstant.AUTHORIZATION_SUPPORT_USER})
  public ListCompanyResponse listCompanies(@RequestParam int offset, @RequestParam int limit) {
    CompanyList companyList = companyService.listCompanies(offset, limit);
    return new ListCompanyResponse(companyList);
  }

  @GetMapping(path = "/get")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_ACCOUNT_SERVICE,
      AuthConstant.AUTHORIZATION_BOT_SERVICE,
      AuthConstant.AUTHORIZATION_WHOAMI_SERVICE,
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_WWW_SERVICE,
      AuthConstant.AUTHORIZATION_ICAL_SERVICE
  })
  public GenericCompanyResponse getCompany(@RequestParam("company_id") String companyId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyDirectory(companyId);
    }
    CompanyDto companyDto = companyService.getCompany(companyId);
    return new GenericCompanyResponse(companyDto);
  }

  @PutMapping(path = "/update")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericCompanyResponse updateCompany(
      @RequestBody @Validated({Group1.class}) CompanyDto companyDto) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(companyDto.getId());
    }
    CompanyDto updatedCompanyDto = companyService.updateCompany(companyDto);
    return new GenericCompanyResponse(updatedCompanyDto);
  }
}
