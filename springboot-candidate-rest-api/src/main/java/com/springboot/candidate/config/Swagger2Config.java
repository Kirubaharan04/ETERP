package com.springboot.candidate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class Swagger2Config {
	
	@Bean
	public Docket swaggerConfig() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.candidate"))
				.build();
				
				
				
				
				/* .paths(PathSelectors.ant("/api/candidate/*"))
				.build()
				.apiInfo(new ApiInfo("movies api", "movie management api description", 
						"1.0", "http://coderulagam.com/termsofuse", 
						new Contact("name", "url", "info@coderulagam.com"), 
						"opensource", "http://coderulagam.com/license", 
						Collections.emptyList()));*/

	}

}
