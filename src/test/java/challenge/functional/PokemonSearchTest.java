package challenge.functional;

import challenge.Application;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
        features = "src/test/resources/challenge/pokemon/")
public class PokemonSearchTest {


}