package com.definesys.dsgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = {"com.definesys.mpaas","com.definesys.dsgc"})
@SpringBootApplication
@EnableTransactionManagement
public class DsgcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsgcApplication.class, args);
	}

}
