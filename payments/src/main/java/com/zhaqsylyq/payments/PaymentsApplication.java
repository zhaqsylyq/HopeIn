package com.zhaqsylyq.payments;

import com.zhaqsylyq.payments.dto.PaymentsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {PaymentsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Payments microservice REST API Documentation",
				description = "HopIn Payments microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Zhaksylyk Chalgimbayev",
						email = "zhaksylyk.chalgimbayev@nu.edu.kz",
						url = "https://dummy.kz"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "HopIn Payments microservice REST API Documentation",
				url = "https://dummy.kz"
		)
)
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

}
