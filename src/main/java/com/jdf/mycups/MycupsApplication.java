package com.jdf.mycups;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.jdf.mycups.dao")
@EnableSwagger2
public class MycupsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycupsApplication.class, args);
	}
}
