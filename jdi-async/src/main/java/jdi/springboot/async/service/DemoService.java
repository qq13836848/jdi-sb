package jdi.springboot.async.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

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

  private static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
