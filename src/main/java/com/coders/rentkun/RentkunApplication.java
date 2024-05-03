package com.coders.rentkun;

import com.coders.rentkun.services.impl.VehicleLogoServiceImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class RentkunApplication {
    @Resource
    VehicleLogoServiceImpl vehicleLogoService;

    public static void main(String[] args) {
        SpringApplication.run(RentkunApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RentKun API")
                        .version("1.0")
                        .description("API for RentKun application")
                        .termsOfService("https://rentkun.com/terms")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
