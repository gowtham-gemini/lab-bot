package net.apporbit.lab.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configurations will be added here.
 */
@Configuration
@ComponentScan(basePackages = "net.apporbit.lab.bot.web.resource")
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@Import(SwaggerUiConfig.class)
public class SwaggerConfig {
    /**
     * Application information.
     * @return ApiInfor entity
     */
    @Bean
    ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
        "Lab-Bot",
        "Lab Management API",
        "1.0.0",
        "",
        "Gowtham",
        "N/A",
        "N/A");
        return apiInfo;
    }

    /**
     * Custom implementation configuration.
     * @return Docket entity
     */
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

}
