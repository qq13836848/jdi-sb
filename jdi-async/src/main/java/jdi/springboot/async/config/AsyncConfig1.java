package jdi.springboot.async.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig1 {

  public static final String EXECUTOR_ONE_BEAN_NAME = "executor-one";
  public static final String EXECUTOR_TWO_BEAN_NAME = "executor-two";

  public static class ExecutorOneConfiguration {

    @Bean(name = EXECUTOR_ONE_BEAN_NAME + "-properties")
    @Primary
    @ConfigurationProperties(prefix = "spring.task.execution-one")
    public TaskExecutionProperties taskExecutionProperties() {
      return new TaskExecutionProperties();
    }

    @Bean(name = EXECUTOR_ONE_BEAN_NAME)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
      TaskExecutorBuilder builder = createTaskExecutorBuilder(this.taskExecutionProperties());
      return builder.build();
    }
  }

  @Configuration
  public static class ExecutorTwoConfiguration {

    @Bean(name = EXECUTOR_TWO_BEAN_NAME + "-properties")
    @ConfigurationProperties(prefix = "spring.task.executor-two")
    public TaskExecutionProperties taskExecutionProperties() {
      return new TaskExecutionProperties();
    }

    @Bean(name = EXECUTOR_TWO_BEAN_NAME)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
      TaskExecutorBuilder bulder = createTaskExecutorBuilder(this.taskExecutionProperties());
      return bulder.build();
    }
  }

  private static TaskExecutorBuilder createTaskExecutorBuilder(TaskExecutionProperties properties) {
    TaskExecutionProperties.Pool pool = properties.getPool();
    TaskExecutorBuilder builder =
        new TaskExecutorBuilder()
            .queueCapacity(pool.getQueueCapacity())
            .corePoolSize(pool.getCoreSize())
            .maxPoolSize(pool.getMaxSize())
            .allowCoreThreadTimeOut(pool.isAllowCoreThreadTimeout())
            .keepAlive(pool.getKeepAlive())
            .awaitTermination(properties.getShutdown().isAwaitTermination())
            .awaitTerminationPeriod(properties.getShutdown().getAwaitTerminationPeriod())
            .threadNamePrefix(properties.getThreadNamePrefix());

    return builder;
  }
}
