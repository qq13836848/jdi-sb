package jdi.springboot.mapstruct;

import jdi.springboot.mapstruct.bo.UserDetailBO;
import jdi.springboot.mapstruct.convert.UserConvert;
import jdi.springboot.mapstruct.dataobject.UserDO;

public class UserDetailBOTest {
  public static void main(String[] args) {
    UserDO userDO = new UserDO().setId(1).setUsername("Jacky").setPassword("123456");
    UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetail(userDO);
    System.out.println(userDetailBO);
  }
}
