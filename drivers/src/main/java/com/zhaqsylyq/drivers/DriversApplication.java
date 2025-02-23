package com.zhaqsylyq.drivers;

import com.zhaqsylyq.drivers.dto.DriversContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Drivers microservice REST API Documentation",
				description = "HopIn Drivers microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Zhaksylyk Chalgimbayev",
						email = "zhaksylyk.chalgimbayev@nu.edu.kz",
						url = "https://github.com/zhaqsylyq"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "HopIn Drivers microservice REST API Documentation",
				url = "https://github.com/zhaqsylyq"
		)
)
@EnableConfigurationProperties(value = {DriversContactInfoDto.class})
public class DriversApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriversApplication.class, args);
	}

}
