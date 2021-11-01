package com.lu.panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class PanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanelApplication.class, args);
    }

}
