package net.apporbit.lab.bot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot application configuration class.
 */
@Configuration
@ComponentScan
@Import(RepositoryRestMvcAutoConfiguration.class)
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class ApplicationConfig {


    /**
     * Default spring boot main method.
     * @param args to pass from command line
     * @throws Exception if any error
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
