package std.staffjoy.common.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import std.staffjoy.common.aop.SentryClientAspect;
import std.staffjoy.common.config.StaffjoyConfig;
import std.staffjoy.common.error.GlobalExceptionTranslator;

/**
 * Use this common config for Rest API
 */
@Configuration
@Import(value = {StaffjoyConfig.class, SentryClientAspect.class, GlobalExceptionTranslator.class})
public class StaffjoyRestConfig {

}
