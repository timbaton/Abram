package mytests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    App app() {
        return new App();
        // да, добавили в mytests.App name,
        // конструктор и toString return new WebApp("Cool APP!");
    }
}
