package std.staffjoy.faraday.config;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "staffjoy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffjoyPropreties {

  @NotNull
  private String signingSecret;
}
