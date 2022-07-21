package com.lmf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching
public class LMFApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private LMFConfiguration lmfConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(LMFApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder){
        return builder.sources(LMFApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        lmfConfiguration.initialize();
        log.info("LMFApplication service online");
    }
}
