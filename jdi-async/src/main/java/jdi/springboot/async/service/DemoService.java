package jdi.springboot.async.service;

import jdi.springboot.async.config.AsyncConfig1;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Service
@Log4j2
public class DemoService {

  public Integer execute01() {
    log.info("[execute01]");
    sleep(10);
    return 1;
  }

  public Integer execute02() {
    log.info("[execute02]");
    sleep(5);
    return 2;
  }

  @Async
  public Integer execute01Async() {
    return execute01();
  }

  @Async
  public Integer execute02Async() {
    return execute02();
  }

  @Async
  public Future<Integer> execute01AsyncWithFutures() {
    return AsyncResult.forValue(this.execute01());
  }

  @Async
  public Future<Integer> execute02AsyncWithFutures() {
    return AsyncResult.forValue(this.execute02());
  }

  public ListenableFuture<Integer> execute01AsyncWithListenableFutures() {
    try {
      return AsyncResult.forValue(this.execute02());
    } catch (Throwable ex) {
      return AsyncResult.forExecutionException(ex);
    }
  }

  private static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Async
  public Integer zhaodaoNvpengyou() {
    throw new RuntimeException("no nvpengyou");
  }

  @Async(AsyncConfig1.EXECUTOR_ONE_BEAN_NAME)
  public Integer execute001() {
    log.info("[execute001]");
    return 1;
  }

  @Async(AsyncConfig1.EXECUTOR_TWO_BEAN_NAME)
  public Integer execute002() {
    log.info("[execute002]");
    return 2;
  }
}
