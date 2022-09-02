package jdi.springboot.springmvc.controller.dto;

import jdi.springboot.springmvc.constants.GenderEnum;
import jdi.springboot.springmvc.validators.InEnum;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author longguo
 */
@Data
@ToString
public class UserUpdateGenderDTO {

  @NotNull(message = "用户编号不能为空")
  private Integer id;

  @NotNull(message = "性别不能为空")
  @InEnum(value = GenderEnum.class, message = "性别必须是{value}")
  private Integer gender;
}
