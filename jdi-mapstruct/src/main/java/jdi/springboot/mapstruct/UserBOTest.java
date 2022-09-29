package jdi.springboot.mapstruct;

import jdi.springboot.mapstruct.bo.UserBO;
import jdi.springboot.mapstruct.convert.UserConvert;
import jdi.springboot.mapstruct.dataobject.UserDO;

public class UserBOTest {
  public static void main(String[] args) {
    UserDO userDO = new UserDO().setId(1).setUsername("Jacky").setPassword("123456");
    UserBO userBO = UserConvert.INSTANCE.convert(userDO);
    System.out.println(userBO);
  }
}
