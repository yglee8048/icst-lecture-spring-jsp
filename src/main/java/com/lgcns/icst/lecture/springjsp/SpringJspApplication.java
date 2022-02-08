package com.lgcns.icst.lecture.springjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJspApplication.class, args);
	}

}
