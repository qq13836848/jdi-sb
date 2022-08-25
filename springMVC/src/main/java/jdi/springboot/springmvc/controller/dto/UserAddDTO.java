package jdi.springboot.springmvc.controller.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserAddDTO {

    private String username;

    private String password;
}
