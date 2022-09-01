package jdi.springboot.springmvc.config;

import jdi.springboot.springmvc.interceptor.FirstInterceptor;
import jdi.springboot.springmvc.interceptor.SecondInterceptor;
import jdi.springboot.springmvc.interceptor.ThirdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class SpringMVCConfiguration implements WebMvcConfigurer {

  @Bean
  public FirstInterceptor firstInterceptor() {
    return new FirstInterceptor();
  }

  @Bean
  public SecondInterceptor secondInterceptor() {
    return new SecondInterceptor();
  }

  @Bean
  public ThirdInterceptor thirdInterceptor() {
    return new ThirdInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(firstInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(secondInterceptor()).addPathPatterns("/users/current_user");
    registry.addInterceptor(thirdInterceptor()).addPathPatterns("/**");
  }

  @Bean
  public ServletRegistrationBean testServlet01() {
    ServletRegistrationBean bean =
        new ServletRegistrationBean<>(
            new HttpServlet() {
              @Override
              protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                log.info("[doGet][uri: {}]", req.getRequestURI());
              }
            });
    bean.setUrlMappings(Collections.singleton("/test/01"));
    return bean;
  }

  @Bean
  public FilterRegistrationBean getFilterRegistrationBean() {
    FilterRegistrationBean bean =
        new FilterRegistrationBean(
            new Filter() {
              @Override
              public void doFilter(
                  ServletRequest servletRequest,
                  ServletResponse servletResponse,
                  FilterChain filterChain)
                  throws IOException, ServletException {
                log.info("[doFilter]");
                filterChain.doFilter(servletRequest, servletResponse);
              }
            });
    bean.setUrlPatterns(Collections.singleton("/test/*"));
    return bean;
  }

  @Bean
  public ServletListenerRegistrationBean<?> getListenerRegistrationBean() {
    return new ServletListenerRegistrationBean(
        new ServletContextListener() {
          @Override
          public void contextInitialized(ServletContextEvent sce) {
            log.info("[contextInitialized]");
          }

          @Override
          public void contextDestroyed(ServletContextEvent sce) {
            ServletContextListener.super.contextDestroyed(sce);
          }
        });
  }

  //  @Override
  //  public void addCorsMappings(CorsRegistry registry) {
  //    registry
  //        .addMapping(("/**"))
  //        .allowedOrigins("*")
  //        .allowCredentials(true)
  //        .allowedMethods("*")
  //        .allowedHeaders("*")
  //        .exposedHeaders("*")
  //        .maxAge(1800L);
  //  }

  public FilterRegistrationBean<CorsFilter> corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Collections.singletonList("*"));
    config.setAllowCredentials(true);
    config.addAllowedMethod("*");
    config.setAllowedHeaders(Collections.singletonList("*"));
    config.setMaxAge(1800L);
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean<CorsFilter> registration =
        new FilterRegistrationBean(new CorsFilter((source)));
    registration.setOrder(0);
    return registration;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // xml
    Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
    xmlBuilder.indentOutput(true);
    converters.add(new MappingJackson2HttpMessageConverter(xmlBuilder.build()));
  }
}
