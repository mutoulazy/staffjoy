package std.staffjoy.common.config;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "staffjoy.common")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffjoyProps {

  @NotBlank
  private String sentryDsn;
  @NotBlank
  // DeployEnvVar is set by Kubernetes during a new deployment so we can identify the code version
  private String deployEnv;
}
