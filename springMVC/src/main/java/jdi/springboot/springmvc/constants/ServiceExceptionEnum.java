package jdi.springboot.springmvc.constants;

import lombok.Getter;

@Getter
public enum ServiceExceptionEnum {
  SUCCESS(0, "成功"),
  SYS_ERROR(2001001000, "服务端发生异常"),
  MISSING_REQUEST_PARAM_ERROR(2001001001, "参数缺失"),

  USER_NOT_FOUND(1001002000, "用户不存在"),

  INVALID_REQUEST_PARAM_ERROR(2001001002, "请求参数不合法");

  private int code;

  private String message;

  ServiceExceptionEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
