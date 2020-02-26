package std.staffjoy.company.dto.worker;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import std.staffjoy.common.api.BaseResponse;
import std.staffjoy.company.dto.worker.WorkerOfList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GetWorkerOfResponse extends BaseResponse {

  private WorkerOfList workerOfList;
}
