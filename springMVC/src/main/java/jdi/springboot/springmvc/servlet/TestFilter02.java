package jdi.springboot.springmvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/test/*")
@Slf4j
public class TestFilter02 implements Filter {
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    log.info("[doFilter02]");
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
