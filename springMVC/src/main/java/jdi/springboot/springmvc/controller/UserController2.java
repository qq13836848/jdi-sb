package jdi.springboot.springmvc.controller;

import jdi.springboot.springmvc.constants.ServiceExceptionEnum;
import jdi.springboot.springmvc.controller.dto.UserAddDTO;
import jdi.springboot.springmvc.controller.dto.UserUpdateDTO;
import jdi.springboot.springmvc.controller.vo.UserVO;
import jdi.springboot.springmvc.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** user api */
@RestController
@RequestMapping("/users2")
@Slf4j
public class UserController2 {

  @GetMapping("/list")
  public List<UserVO> list() {
    List result = new ArrayList<UserVO>();
    result.add(new UserVO(1, "admin"));
    result.add(new UserVO(1, "admin"));
    result.add(new UserVO(1, "admin"));
    return result;
  }

  @GetMapping("/get")
  public UserVO get(@RequestParam("id") Integer id) {
    return new UserVO(id, UUID.randomUUID().toString());
  }

  @PostMapping("/add")
  public Integer add(UserAddDTO addDTO) {
    log.debug(addDTO.toString());
    Integer returnId = UUID.randomUUID().hashCode();
    return returnId;
  }

  @PostMapping("/update")
  public Boolean update(UserUpdateDTO updateDTO) {
    log.debug(updateDTO.toString());
    Boolean success = true;
    return success;
  }

  @GetMapping("/exception01")
  public UserVO exception01() {
    throw new NullPointerException("没有粗面鱼丸");
  }

  @GetMapping("/exception02")
  public UserVO exception02() {
    throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
  }

  @GetMapping("/do_something")
  public void doSomething() {
    log.info("[doSomething]");
  }

  public UserVO currentUser() {
    log.info("[currentUser]");
    return new UserVO(10, "currentUser");
  }

  @GetMapping("/exception03")
  public UserVO exception03() {
    log.info("[exception03]");
    throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
  }
}
