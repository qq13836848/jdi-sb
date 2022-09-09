package jdi.springboot.mybatis.mapper;

import jdi.springboot.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserMapper {

  int insert(UserDO user);

  int updateById(UserDO user);

  int deleteById(@Param("id") Integer id);

  UserDO selectById(@Param("id") Integer id);

  UserDO selectByUsername(@Param("username") String username);

  List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);
}
