package jdi.springboot.springmvc.controller.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserUpdateDTO {

    private Integer id;

    private String userName;

    private String password;

}
