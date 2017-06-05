package ngSpring.demo;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

//tag::spring-app[]
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
//end::spring-app[]
//tag::swagger-docs[]
@EnableSwagger2
//tag::spring-app[]
public class AngularSpringApplication extends SpringBootServletInitializer {
//end::spring-app[]

    @Bean
    public Docket ngSpringApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ng-spring-boot-api")
                .apiInfo(apiInfo())
                .select()
                .paths(apiPaths())
                .build();
    }

    private Predicate<String> apiPaths() {
        return or(
                regex("/api/event.*"),
                regex("/api/login.*"),
                regex("/api/user.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Angular SpringBoot Demo API")
                .description("Sample AngularJS web app with SpringBoot REST backend")
                .termsOfServiceUrl("https://github.com/holisticon/continous-delivery-demo")
                .contact("info@holisticon.de")
                .license("MIT")
                .licenseUrl("https://raw.githubusercontent.com/holisticon/continous-delivery-demo/master/LICENSE")
                .version("0.2.0")
                .build();
    } //end::swagger-docs[]

    //tag::spring-app[]
    public static void main(String[] args) {
        SpringApplication.run(AngularSpringApplication.class, args);
    }
    //end::spring-app[]
}
