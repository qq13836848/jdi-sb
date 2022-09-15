package jdi.springboot.async.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@Log4j2
public class DemoServiceTest {

  @Autowired private DemoService demoService;

  @Test
  public void test01() {
    long now = System.currentTimeMillis();
    log.info("[task01][start]");
    demoService.execute01();
    demoService.execute02();
    log.info("[task02][end. use " + (System.currentTimeMillis() - now) + " ms]");
  }

  @Test
  public void test02() {
    long now = System.currentTimeMillis();
    log.info("[task02][start]");
    demoService.execute01Async();
    demoService.execute02Async();

    log.info("[task02][end. use: {} ms]", System.currentTimeMillis() - now);
  }

  @Test
  public void test03() throws ExecutionException, InterruptedException {
    long now = System.currentTimeMillis();
    log.info("[task03][start]");

    Future<Integer> execute01Result = demoService.execute01AsyncWithFutures();
    Future<Integer> execute02Result = demoService.execute02AsyncWithFutures();

    execute02Result.get();
    execute01Result.get();
    log.info("[task03][end. use: {} ms]", System.currentTimeMillis() - now);
  }
}
