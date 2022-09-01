package jdi.springboot.springmvc.controller;

import jdi.springboot.springmvc.controller.vo.UserVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
// @CrossOrigin(origins = "*", allowCredentials = "true")
public class TestController {

  @GetMapping("/test/01")
  //  @CrossOrigin(allowCredentials = "false")
  public String test01() {
    return "test01";
  }

  @GetMapping("/test/02")
  public String test02() {
    return "test02";
  }

  @PostMapping(
      value = "/test/add",
      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public UserVO add(@RequestBody UserVO user) {
    return user;
  }
}
