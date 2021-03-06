package std.staffjoy.ical.service;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import io.sentry.SentryClient;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import std.staffjoy.common.auth.AuthConstant;
import std.staffjoy.common.env.EnvConfig;
import std.staffjoy.common.error.ServiceException;
import std.staffjoy.company.client.CompanyClient;
import std.staffjoy.company.dto.WorkerShiftListRequest;
import std.staffjoy.company.dto.company.CompanyDto;
import std.staffjoy.company.dto.company.GenericCompanyResponse;
import std.staffjoy.company.dto.shift.GenericShiftListResponse;
import std.staffjoy.company.dto.shift.ShiftList;
import std.staffjoy.company.dto.worker.GenericWorkerResponse;
import std.staffjoy.company.dto.worker.WorkerDto;
import std.staffjoy.ical.model.Cal;

@Service
public class ICalService {

  static final ILogger logger = SLoggerFactory.getLogger(ICalService.class);

  @Autowired
  private CompanyClient companyClient;

  @Autowired
  SentryClient sentryClient;

  @Autowired
  EnvConfig envConfig;

  public Cal getCalByUserId(String userId) {
    GenericWorkerResponse workerResponse = null;
    try {
      workerResponse = companyClient
          .getWorkerTeamInfo(AuthConstant.AUTHORIZATION_ICAL_SERVICE, null, userId);
    } catch (Exception ex) {
      String errMsg = "unable to get team info";
      handleErrorAndThrowException(ex, errMsg);
    }
    if (!workerResponse.isSuccess()) {
      handleErrorAndThrowException(workerResponse.getMessage());
    }
    WorkerDto workerDto = workerResponse.getWorker();

    GenericCompanyResponse genericCompanyResponse = null;
    try {
      genericCompanyResponse = companyClient
          .getCompany(AuthConstant.AUTHORIZATION_ICAL_SERVICE, workerDto.getCompanyId());
    } catch (Exception ex) {
      String errMsg = "unable to get company";
      handleErrorAndThrowException(ex, errMsg);
    }
    if (!genericCompanyResponse.isSuccess()) {
      handleErrorAndThrowException(genericCompanyResponse.getMessage());
    }

    CompanyDto companyDto = genericCompanyResponse.getCompany();

    WorkerShiftListRequest workerShiftListRequest = WorkerShiftListRequest.builder()
        .companyId(workerDto.getCompanyId())
        .teamId(workerDto.getTeamId())
        .workerId(workerDto.getUserId())
        .shiftStartAfter(Instant.now().minus(30, ChronoUnit.DAYS))
        .shiftStartBefore(Instant.now().plus(90, ChronoUnit.DAYS))
        .build();
    GenericShiftListResponse shiftListResponse = null;
    try {
      shiftListResponse = companyClient
          .listWorkerShifts(AuthConstant.AUTHORIZATION_ICAL_SERVICE, workerShiftListRequest);
    } catch (Exception ex) {
      String errMsg = "unable to get worker shifts";
      handleErrorAndThrowException(ex, errMsg);
    }
    if (!shiftListResponse.isSuccess()) {
      handleErrorAndThrowException(shiftListResponse.getMessage());
    }
    ShiftList shiftList = shiftListResponse.getShiftList();

    Cal cal = Cal.builder()
        .companyName(companyDto.getName())
        .shiftList(shiftList.getShifts())
        .build();

    return cal;

  }

  void handleErrorAndThrowException(String errMsg) {
    logger.error(errMsg);
    sentryClient.sendMessage(errMsg);
    throw new ServiceException(errMsg);
  }

  void handleErrorAndThrowException(Exception ex, String errMsg) {
    logger.error(errMsg, ex);
    sentryClient.sendException(ex);
    throw new ServiceException(errMsg, ex);
  }
}

