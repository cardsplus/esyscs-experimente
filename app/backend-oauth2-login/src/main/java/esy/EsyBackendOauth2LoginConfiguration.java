package esy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class EsyBackendOauth2LoginConfiguration {

    @Bean
    @SuppressWarnings("java:S1612")
    public WebSecurityCustomizer securityCustomizer() {
        return web -> {
            // do not handle static resources
            web.ignoring().requestMatchers("/static/**");
            // do not handle version endpoints
            web.ignoring().requestMatchers("/version/**");
        };
    }
}
