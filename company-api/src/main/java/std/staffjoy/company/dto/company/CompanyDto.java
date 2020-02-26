package std.staffjoy.company.dto.company;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import std.staffjoy.common.validataion.DayOfWeek;
import std.staffjoy.common.validataion.Group1;
import std.staffjoy.common.validataion.Group2;
import std.staffjoy.common.validataion.Timezone;

/**
 * 对检验注解采用group1、2进行分组
 * 在新增时不需要对id字段进行校验 Group2
 * 在更新操作时要对id字段进行校验 Group1
 * http://blog.joylau.cn/2019/01/26/SpringBoot-Validated/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

  @NotBlank(groups = {Group1.class})
  private String id;
  @NotBlank(groups = {Group1.class, Group2.class})
  private String name;
  private boolean archived;
  @Timezone(groups = {Group1.class, Group2.class})
  @NotBlank(groups = {Group1.class, Group2.class})
  private String defaultTimezone;
  @DayOfWeek(groups = {Group1.class, Group2.class})
  @NotBlank(groups = {Group1.class, Group2.class})
  private String defaultDayWeekStarts;
}
