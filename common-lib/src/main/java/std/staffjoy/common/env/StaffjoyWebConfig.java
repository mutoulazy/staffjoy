package std.staffjoy.common.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import std.staffjoy.common.aop.SentryClientAspect;
import std.staffjoy.common.config.StaffjoyConfig;

/**
 * Use this common config for Web App
 */
@Configuration
@Import(value = {StaffjoyConfig.class, SentryClientAspect.class,})
public class StaffjoyWebConfig {

}
