package jdi.springboot.mybatis.dataobject;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserDO {

  private Integer id;

  private String username;

  private String password;

  private Date createTime;
}
