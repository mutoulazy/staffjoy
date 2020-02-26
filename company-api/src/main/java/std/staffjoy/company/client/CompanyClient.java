package std.staffjoy.company.client;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.validataion.Group1;
import std.staffjoy.common.validataion.Group2;
import std.staffjoy.company.CompanyConstant;
import std.staffjoy.company.dto.GetAssociationResponse;
import std.staffjoy.company.dto.WorkerShiftListRequest;
import std.staffjoy.company.dto.admin.GetAdminOfResponse;
import std.staffjoy.company.dto.admin.ListAdminResponse;
import std.staffjoy.company.dto.company.CompanyDto;
import std.staffjoy.company.dto.company.GenericCompanyResponse;
import std.staffjoy.company.dto.company.ListCompanyResponse;
import std.staffjoy.company.dto.directory.DirectoryEntryDto;
import std.staffjoy.company.dto.directory.DirectoryEntryRequest;
import std.staffjoy.company.dto.directory.GenericDirectoryResponse;
import std.staffjoy.company.dto.directory.ListDirectoryResponse;
import std.staffjoy.company.dto.directory.NewDirectoryEntry;
import std.staffjoy.company.dto.job.CreateJobRequest;
import std.staffjoy.company.dto.job.GenericJobResponse;
import std.staffjoy.company.dto.job.JobDto;
import std.staffjoy.company.dto.job.ListJobResponse;
import std.staffjoy.company.dto.shift.BulkPublishShiftsRequest;
import std.staffjoy.company.dto.shift.CreateShiftRequest;
import std.staffjoy.company.dto.shift.GenericShiftListResponse;
import std.staffjoy.company.dto.shift.GenericShiftResponse;
import std.staffjoy.company.dto.shift.ShiftDto;
import std.staffjoy.company.dto.shift.ShiftListRequest;
import std.staffjoy.company.dto.team.CreateTeamRequest;
import std.staffjoy.company.dto.team.GenericTeamResponse;
import std.staffjoy.company.dto.team.ListTeamResponse;
import std.staffjoy.company.dto.team.TeamDto;
import std.staffjoy.company.dto.worker.GenericWorkerResponse;
import std.staffjoy.company.dto.worker.GetWorkerOfResponse;
import std.staffjoy.company.dto.worker.ListWorkerResponse;
import std.staffjoy.company.dto.worker.WorkerDto;

@FeignClient(name = CompanyConstant.SERVICE_NAME, path = "/v1/company", url = "${staffjoy.company-service-endpoint}")
public interface CompanyClient {

  // Company Apis
  @PostMapping(path = "/create")
  GenericCompanyResponse createCompany(
      @RequestHeader(AuthConstant.CURRENT_USER_HEADER) String authz,
      @RequestBody @Validated({Group2.class}) CompanyDto companyDto);

  @PutMapping(path = "/update")
  GenericCompanyResponse updateCompany(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated({Group1.class}) CompanyDto companyDto);

  @GetMapping(path = "/list")
  ListCompanyResponse listCompanies(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam int offset,
      @RequestParam @Min(0) int limit);

  @GetMapping(path = "/get")
  GenericCompanyResponse getCompany(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam("company_id") @NotBlank String companyId);


  // Admin Apis
  @PostMapping(path = "/admin/create")
  GenericDirectoryResponse createAdmin(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated DirectoryEntryRequest request);

  @DeleteMapping(path = "/admin/delete")
  BaseResponse deleteAdmin(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated DirectoryEntryRequest request);


  @GetMapping(path = "/admin/list")
  ListAdminResponse listAdmins(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId);

  @GetMapping(path = "/admin/get")
  GenericDirectoryResponse getAdmin(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String userId);

  @GetMapping(path = "/admin/admin_of")
  GetAdminOfResponse getAdminOf(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String userId);


  // Directory Apis
  @PostMapping(path = "/directory/create")
  GenericDirectoryResponse createDirectory(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated NewDirectoryEntry request);

  @PutMapping(path = "/directory/update")
  GenericDirectoryResponse updateDirectoryEntry(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated DirectoryEntryDto request);

  @GetMapping(path = "/directory/list")
  ListDirectoryResponse listDirectories(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam String companyId,
      @RequestParam int offset,
      @RequestParam @Min(0) int limit);

  @GetMapping(path = "/directory/get")
  GenericDirectoryResponse getDirectoryEntry(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam String companyId, @RequestParam String userId);

  /**
   * 获取员工与组关联的详细信息
   */
  @GetMapping(path = "/directory/get_associations")
  GetAssociationResponse getAssociations(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam String companyId,
      @RequestParam int offset,
      @RequestParam @Min(0) int limit);


  // Team Apis
  @PostMapping(path = "/team/create")
  GenericTeamResponse createTeam(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated CreateTeamRequest request);

  @PutMapping(path = "/team/update")
  GenericTeamResponse updateTeam(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated TeamDto teamDto);

  @GetMapping(path = "/team/list")
  ListTeamResponse listTeams(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId);

  @GetMapping(path = "/team/get")
  GenericTeamResponse getTeam(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String teamId);

  /**
   * 根据公司和用户的id获取work的信息
   */
  @GetMapping(path = "/team/get_worker_team_info")
  GenericWorkerResponse getWorkerTeamInfo(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam(required = false) String companyId,
      @RequestParam @NotBlank String userId);


  // Worker Apis
  @PostMapping(path = "/worker/create")
  GenericDirectoryResponse createWorker(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated WorkerDto workerDto);

  @DeleteMapping(path = "/worker/delete")
  BaseResponse deleteWorker(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated WorkerDto workerDto);

  @GetMapping(path = "/worker/list")
  ListWorkerResponse listWorkers(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String teamId);

  @GetMapping(path = "/worker/get")
  GenericDirectoryResponse getWorker(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String teamId,
      @RequestParam @NotBlank String userId);

  @GetMapping(path = "/worker/get_worker_of")
  GetWorkerOfResponse getWorkerOf(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String userId);


  // Job Apis
  @PostMapping(path = "/job/create")
  GenericJobResponse createJob(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated CreateJobRequest request);

  @PutMapping(path = "/job/update")
  GenericJobResponse updateJob(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated JobDto jobDto);

  @GetMapping(path = "/job/list")
  ListJobResponse listJobs(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String teamId);

  @GetMapping(path = "/job/get")
  GenericJobResponse getJob(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String jobId,
      @RequestParam @NotBlank String companyId,
      @RequestParam @NotBlank String teamId);


  // Shift Apis
  @PostMapping(path = "/shift/create")
  GenericShiftResponse createShift(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated CreateShiftRequest request);

  @PostMapping(path = "/shift/bulk_publish")
  GenericShiftListResponse bulkPublishShifts(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated BulkPublishShiftsRequest request);

  @PostMapping(path = "/shift/list_worker_shifts")
  GenericShiftListResponse listWorkerShifts(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated WorkerShiftListRequest request);

  @PostMapping(path = "/shift/list_shifts")
  GenericShiftListResponse listShifts(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated ShiftListRequest request);

  @DeleteMapping(path = "/shift/delete")
  BaseResponse deleteShift(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam String shiftId, @RequestParam String teamId,
      @RequestParam String companyId);

  @PutMapping(path = "/shift/update")
  GenericShiftResponse updateShift(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestBody @Validated ShiftDto shiftDto);

  @GetMapping(path = "/shift/get")
  GenericShiftResponse getShift(
      @RequestHeader(AuthConstant.AUTHORIZATION_HEADER) String authz,
      @RequestParam @NotBlank String shiftId,
      @RequestParam @NotBlank String teamId,
      @RequestParam @NotBlank String companyId);
}
