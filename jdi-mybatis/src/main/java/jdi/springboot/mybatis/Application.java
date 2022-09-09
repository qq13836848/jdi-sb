package jdi.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "jdi.springboot.mybatis.mapper")
public class Application {}
