package std.staffjoy.faraday.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorPage {

  private String title;
  private String explanation;
  private int headerCode;
  private String linkText;
  private String linkHref;
  private String sentryErrorId;
  private String sentryPublicDsn;
  private String imageBase64;
}
