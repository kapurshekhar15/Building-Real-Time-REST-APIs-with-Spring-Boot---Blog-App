package com.spirngboot.blog.Spring;

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
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App REST APIs",
                description = "Spring Boot Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Shekhar",
                        email = "kapurshekhar15@gmail.com",
                        url = "https://www.linkedin.com/in/shekhar-kapur-a58b9b159/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.linkedin.com/in/shekhar-kapur-a58b9b159/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Blog App REST APIs Documentation",
                url = "https://github.com/kapurshekhar15/Building-Real-Time-REST-APIs-with-Spring-Boot---Blog-App"
        )
)
public class SpringBootBlogRestApisApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogRestApisApplication.class, args);
    }

}
