package jdi.springboot.springmvc.controller.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.Assert;

import java.io.Serializable;

@Data
@ToString
public class CommonResult<T> implements Serializable {

  public static Integer CODE_SUCCESS = 0;

  private Integer code;

  private String message;

  private T data;

  public static <T> CommonResult<T> error(CommonResult<?> result) {
    return error(result.getCode(), result.getMessage());
  }

  public static <T> CommonResult<T> error(Integer code, String message) {
    Assert.isTrue(!CODE_SUCCESS.equals(code), "code 必须是错误的！");
    CommonResult<T> result = new CommonResult<>();
    result.code = code;
    result.message = message;
    return result;
  }

  public static <T> CommonResult<T> success(T data) {
    CommonResult<T> result = new CommonResult<>();
    result.code = CODE_SUCCESS;
    result.data = data;
    result.message = "";
    return result;
  }

  @JsonIgnore
  public boolean isSuccess() {
    return CODE_SUCCESS.equals(code);
  }

  @JsonIgnore
  public boolean isError() {
    return !isSuccess();
  }
}
