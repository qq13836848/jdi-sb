package jdi.springboot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@TableName(value = "mp_users")
@Data
@Accessors(chain = true)
public class UserDO {

  private Integer id;

  private String username;

  private String password;

  private Date createTime;

  @TableLogic private Integer deleted;
}
