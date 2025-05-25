package com.flash.user;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author Yury
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FlashScholars网站API")
                        .description("FlashScholars网站API")
                        .version("v1.0.0")
                        .license(new License()
                                .name("许可协议")
                                .url(""))
                        .contact(new Contact()
                                .name("Beyond Developer")
                                .email(""))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("codearts")
                        .url(""));
    }
}
