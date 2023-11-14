package com.tuyetanh0207;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan
//@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    public GreetingReponse greet(){
        GreetingReponse response = new GreetingReponse(
                 "Hello",
                List.of("Java","golang", "Javascript"),
        new Person("Tuyet Anh")
        );
        return response;

    }


}



