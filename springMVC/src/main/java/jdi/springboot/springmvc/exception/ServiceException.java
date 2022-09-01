package jdi.springboot.springmvc.exception;

import jdi.springboot.springmvc.constants.ServiceExceptionEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private final Integer code;

  public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
    super(serviceExceptionEnum.getMessage());
    this.code = serviceExceptionEnum.getCode();
  }
}
