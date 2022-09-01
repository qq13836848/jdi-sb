package jdi.springboot.springmvc.controller;

import jdi.springboot.springmvc.controller.dto.UserAddDTO;
import jdi.springboot.springmvc.controller.dto.UserUpdateDTO;
import jdi.springboot.springmvc.controller.vo.CommonResult;
import jdi.springboot.springmvc.controller.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * user restful api
 *
 * @author longguo
 */
@RestController
@RequestMapping("/users")
public class UserRestfullController {

  @GetMapping()
  public List<UserVO> list() {
    List result = new ArrayList<UserVO>();
    result.add(new UserVO(1, "admin"));
    result.add(new UserVO(2, "admin"));
    result.add(new UserVO(3, "admin"));
    return result;
  }

  @GetMapping("/{id}")
  public UserVO getUser(@PathVariable("id") Integer id) {
    return new UserVO(id, "admin");
  }

  @PostMapping
  public Integer addUser(UserAddDTO addDTO) {
    Integer result = 1;
    return result;
  }

  @PutMapping("/{id}")
  public Boolean updateUser(@PathVariable("id") Integer id, UserUpdateDTO updateDTO) {
    return true;
  }

  @DeleteMapping("/{id}")
  public Boolean deleteUser(@PathVariable("id") Integer id) {
    return true;
  }

  @GetMapping("/get2")
  public CommonResult<UserVO> get2(@RequestParam("id") Integer id) {
    UserVO user = new UserVO(id, "admin");
    return CommonResult.success(user);
  }
}
