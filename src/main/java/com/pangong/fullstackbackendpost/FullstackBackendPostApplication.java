package com.pangong.fullstackbackendpost;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//define api information for swagger and openapi using annotation
@OpenAPIDefinition(
		info = @Info(
				title = "Springboot my blog REST api",
				description = "the document of the blog rest api",
				version = "v1.0",
				contact = @Contact(
						name = "Pan Gong",
						email = "Pan.Gong@dal.ca",
						url = "https://pangong.dev/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Springboot blog backend source github website",
				url = "https://github.com/panda022/fullstack-backend"
		)
)
public class FullstackBackendPostApplication {

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(FullstackBackendPostApplication.class, args);
	}

}
