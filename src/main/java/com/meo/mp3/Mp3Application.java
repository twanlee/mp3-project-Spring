package com.meo.mp3;

import com.meo.mp3.response.BaseResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Mp3Application {
    @Bean
    public BaseResponse baseResponse() {
        return new BaseResponse();
    }

    public static void main(String[] args) {
        SpringApplication.run(Mp3Application.class, args);
    }

}
