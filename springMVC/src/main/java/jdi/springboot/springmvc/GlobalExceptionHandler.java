package jdi.springboot.springmvc;

import jdi.springboot.springmvc.constants.ServiceExceptionEnum;
import jdi.springboot.springmvc.controller.vo.CommonResult;
import jdi.springboot.springmvc.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(basePackages = "jdi.springboot.springmvc.controller")
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = ServiceException.class)
  public CommonResult serviceExceptionHandler(
      HttpServletRequest request, ServiceException exception) {
    log.debug("[serviceExceptionHandler]", exception);
    return CommonResult.error(exception.getCode(), exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public CommonResult missingServiceExceptionHandler(
      HttpServletRequest request, MissingServletRequestParameterException exception) {
    log.debug("[missingServiceExceptionHandler]", exception);
    return CommonResult.error(
        ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
        ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public CommonResult exceptionHandler(HttpServletRequest request, Exception exception) {
    log.debug("[exceptionHandler]", exception);
    return CommonResult.error(
        ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage());
  }
}
