package std.staffjoy.bot.config;


import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import std.staffjoy.common.async.ContextCopyingDecorator;
import std.staffjoy.common.env.StaffjoyRestConfig;

@Configuration
@EnableAsync
@Import(value = {StaffjoyRestConfig.class})
@SuppressWarnings(value = "Duplicates")
public class AppConfig {

  public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";

  @Bean(name = ASYNC_EXECUTOR_NAME)
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setTaskDecorator(new ContextCopyingDecorator());
    executor.setCorePoolSize(3);
    executor.setMaxPoolSize(5);
    executor.setQueueCapacity(100);
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix("AsyncThread-");
    executor.initialize();
    return executor;
  }

}
