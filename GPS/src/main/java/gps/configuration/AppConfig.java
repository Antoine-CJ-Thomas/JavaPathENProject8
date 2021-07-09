package gps.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * This class allows to configure this application
 */
@Configuration
public class AppConfig {

    @Bean
    public Locale getLocale() {

        Locale.setDefault(Locale.US);

        return Locale.getDefault();
    }
}