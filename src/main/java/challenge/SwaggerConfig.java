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
        String description = "Below there are all the EndPoint and entities that will be necessary for the test.</br>" +
            "there are <b>six operations</b> in total:</br>" +
            "- Five methods you can do about the pokemons.</br>" +
            "- A method for obtaining a list of types that you can associate with each pokemon.";
        return new ApiInfoBuilder()
            .title("Pokemon Challenge")
            .description(description)
            .version("1.0")
            .build();
    }
}