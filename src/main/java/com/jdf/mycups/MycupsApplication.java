package com.jdf.mycups;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jdf.mycups.dao")
public class MycupsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycupsApplication.class, args);
	}
}
