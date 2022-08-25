package jdi.springboot.springmvc.controller;

import jdi.springboot.springmvc.controller.dto.UserAddDTO;
import jdi.springboot.springmvc.controller.dto.UserUpdateDTO;
import jdi.springboot.springmvc.controller.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * user api
 *
 */
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
}
