package jdi.springboot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdi.springboot.mybatisplus.dataobject.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class UserMapperTest {

  @Autowired private UserMapper userMapper;

  @Test
  public void testInsert() {
    UserDO user = new UserDO();
    user.setUsername(UUID.randomUUID().toString());
    user.setPassword("nicai");
    user.setDeleted(0);
    userMapper.insert(user);
  }

  @Test
  public void testUpdateById() {
    UserDO user = new UserDO();
    user.setId(7);
    user.setPassword("wobucai");

    userMapper.updateById(user);
  }

  @Test
  public void testDeleteById() {
    userMapper.deleteById(8);
  }

  @Test
  public void testSelectById() {
    UserDO userDO = userMapper.selectById(8);
    System.out.println(userDO);
  }

  @Test
  public void testSelectByUsername() {
    UserDO yunai = userMapper.selectByUsername("3529b8d9-c16f-48ae-81e6-a5f87fe7ee65");
    System.out.println(yunai);
  }

  @Test
  public void testSelectByIds() {
    List<UserDO> users = userMapper.selectByIds(Arrays.asList(7, 8));
    System.out.println(users);
  }

  @Test
  public void testSelectPageByCreateTime() {
    IPage<UserDO> page = new Page<>(1, 10);
    Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24);
    page = userMapper.selectPageByCreateTime(page, createTime);
    System.out.println(page);
  }
}
