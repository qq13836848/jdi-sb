package jdi.springboot.mapstruct.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserBO {
  private Integer id;

  private String username;

  private String password;
}
