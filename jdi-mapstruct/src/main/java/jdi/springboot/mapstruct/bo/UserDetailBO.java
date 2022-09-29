package jdi.springboot.mapstruct.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDetailBO {
  private Integer userId;
}
