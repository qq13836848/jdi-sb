package jdi.springboot.mybatis;

import jdi.springboot.mybatis.dataobject.UserDO;
import jdi.springboot.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest()
public class UserMapperTest {

  @Autowired private UserMapper userMapper;

  @Test
  public void testInsert() {
    UserDO user = new UserDO();
    user.setUsername(UUID.randomUUID().toString());
    user.setPassword("password");
    user.setCreateTime(new Date());
    userMapper.insert(user);
  }

  @Test
  public void testUpdateById() {
    UserDO user = new UserDO();
    user.setId(8);
    user.setPassword("helloworld");
    userMapper.updateById(user);
  }

  @Test
  public void testDeleteById() {
    userMapper.deleteById(8);
  }

  @Test
  public void testSelectById() {
    UserDO userDO = userMapper.selectById(7);
    System.out.println(userDO);
  }

  @Test
  public void testSelectByUsername() {
    UserDO userDO = userMapper.selectByUsername("9307");
    System.out.println(userDO);
  }

  @Test
  public void testSelectByIds() {
    List<UserDO> users = userMapper.selectByIds(Arrays.asList(7, 8));
    System.out.println(users);
  }
}
