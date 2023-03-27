package jdi.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class Test {

  @Autowired private StringRedisTemplate stringRedisTemplate;

  @org.junit.jupiter.api.Test
  public void testStringSetKey() {
    stringRedisTemplate.opsForValue().set("yunai", "shuai");
  }
}
