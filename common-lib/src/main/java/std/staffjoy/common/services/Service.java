package std.staffjoy.common.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service {

  // Public, Authenticated, or Admin
  private int security;
  // If true, service is suppressed in stage and prod
  private boolean restrictDev;
  // Backend service to query
  private String backendDomain;
  // If true, injects a header for HTML responses telling the browser not to cache HTML
  private boolean noCacheHtml;
}
