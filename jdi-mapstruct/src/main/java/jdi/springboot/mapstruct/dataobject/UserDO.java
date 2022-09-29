package jdi.springboot.mapstruct.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDO {
  private Integer id;

  private String username;

  private String password;
}
