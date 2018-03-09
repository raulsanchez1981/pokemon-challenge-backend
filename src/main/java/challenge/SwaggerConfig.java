package challenge;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName("challenge")
            .apiInfo(apiInfo())
            .select()
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
            .paths((PathSelectors.any()))
            .build();
    }

    private ApiInfo apiInfo() {
        String description = "Below you can find all the EndPoint and entities that will be necessary for the test.</br>" +
            "You can use them as many times as you want and perform the tests you need to then use them in your development.</br>" +
            "You have <b>six operations</b> in total:</br>" +
            "- Five methods you can do about the characters.</br>" +
            "- A method for obtaining a list of powers that you can associate with each character.";
        return new ApiInfoBuilder()
            .title("Challenge for Tools Team")
            .description(description)
            .version("1.0")
            .build();
    }
}