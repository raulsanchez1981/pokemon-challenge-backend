package challenge.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsanchpa on 29/09/2017.
 */
@Component
@PropertySource("classpath:ErrorMessages.properties")
@ConfigurationProperties(prefix = "error")
public class ErrorMessages {

    private final Map<String, String> messages = new HashMap<>();

    public Map<String, String> getMessages() {
        return messages;
    }

    public String getProperty (String key) {
        return this.getMessages().get(key.toLowerCase());
    }
}
