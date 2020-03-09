package std.staffjoy.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.auth.AuthContext;
import std.staffjoy.common.auth.Authorize;
import std.staffjoy.company.dto.WorkerShiftListRequest;
import std.staffjoy.company.dto.shift.BulkPublishShiftsRequest;
import std.staffjoy.company.dto.shift.CreateShiftRequest;
import std.staffjoy.company.dto.shift.GenericShiftListResponse;
import std.staffjoy.company.dto.shift.GenericShiftResponse;
import std.staffjoy.company.dto.shift.ShiftDto;
import std.staffjoy.company.dto.shift.ShiftList;
import std.staffjoy.company.dto.shift.ShiftListRequest;
import std.staffjoy.company.service.PermissionService;
import std.staffjoy.company.service.ShiftService;

@RestController
@RequestMapping("/v1/company/shift")
@Validated
public class ShiftController {

  @Autowired
  ShiftService shiftService;

  @Autowired
  PermissionService permissionService;

  @PostMapping(path = "/create")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericShiftResponse createShift(@RequestBody @Validated CreateShiftRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(request.getCompanyId());
    }

    ShiftDto shiftDto = this.shiftService.createShift(request);

    return new GenericShiftResponse(shiftDto);
  }

  @PostMapping(path = "/list_worker_shifts")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER,
      AuthConstant.AUTHORIZATION_BOT_SERVICE,
      AuthConstant.AUTHORIZATION_ICAL_SERVICE
  })
  public GenericShiftListResponse listWorkerShifts(
      @RequestBody @Validated WorkerShiftListRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      // TODO need confirm
      permissionService.checkPermissionTeamWorker(request.getCompanyId(), request.getTeamId());
    }

    ShiftList shiftList = shiftService.listWorkerShifts(request);

    return new GenericShiftListResponse(shiftList);
  }

  @PostMapping(path = "/list_shifts")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericShiftListResponse listShifts(@RequestBody @Validated ShiftListRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionTeamWorker(request.getCompanyId(), request.getTeamId());
    }

    ShiftList shiftList = shiftService.listShifts(request);

    return new GenericShiftListResponse(shiftList);
  }

  @PostMapping(path = "/bulk_publish")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericShiftListResponse bulkPublishShifts(
      @RequestBody @Validated BulkPublishShiftsRequest request) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionTeamWorker(request.getCompanyId(), request.getTeamId());
    }

    ShiftList shiftList = shiftService.bulkPublishShifts(request);

    return new GenericShiftListResponse(shiftList);
  }

  @GetMapping(path = "/get")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericShiftResponse getShift(@RequestParam String shiftId, @RequestParam String teamId,
      @RequestParam String companyId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionTeamWorker(companyId, teamId);
    }

    ShiftDto shiftDto = shiftService.getShift(shiftId, teamId, companyId);

    return new GenericShiftResponse(shiftDto);
  }

  @PutMapping(path = "/update")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public GenericShiftResponse updateShift(@RequestBody @Validated ShiftDto shiftDto) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionCompanyAdmin(shiftDto.getCompanyId());
    }

    ShiftDto updatedShiftDto = shiftService.updateShift(shiftDto);

    return new GenericShiftResponse(updatedShiftDto);
  }

  @DeleteMapping(path = "/delete")
  @Authorize(value = {
      AuthConstant.AUTHORIZATION_AUTHENTICATED_USER,
      AuthConstant.AUTHORIZATION_SUPPORT_USER
  })
  public BaseResponse deleteShift(@RequestParam String shiftId, @RequestParam String teamId,
      @RequestParam String companyId) {
    if (AuthConstant.AUTHORIZATION_AUTHENTICATED_USER.equals(AuthContext.getAuthz())) {
      permissionService.checkPermissionTeamWorker(companyId, teamId);
    }

    shiftService.deleteShift(shiftId, teamId, companyId);

    return BaseResponse.builder().message("shift deleted").build();
  }
}
