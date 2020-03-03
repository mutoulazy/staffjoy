package std.staffjoy.whoami.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.company.dto.admin.AdminOfList;
import std.staffjoy.company.dto.worker.WorkerOfList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IAmDto {

  private boolean support;
  private String userId;
  private WorkerOfList workerOfList;
  private AdminOfList adminOfList;
}
