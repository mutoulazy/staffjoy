package std.staffjoy.common.async;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 多线程线程间同步context信息
 * https://stackoverflow.com/questions/23732089/how-to-enable-request-scope-in-async-task-executor
 */
public class ContextCopyingDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(Runnable runnable) {
    RequestAttributes context = RequestContextHolder.currentRequestAttributes();
    return () -> {
      try {
        RequestContextHolder.setRequestAttributes(context);
        runnable.run();
      } finally {
        RequestContextHolder.resetRequestAttributes();
      }
    };
  }
}
