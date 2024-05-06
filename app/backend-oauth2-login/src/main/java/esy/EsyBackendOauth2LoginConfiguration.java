package esy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class EsyBackendOauth2LoginConfiguration {

    @Value("${oauth2.clientId}")
    private String clientId;

    @Value("${oauth2.clientSecret}")
    private String clientSecret;

    @Bean
    public WebSecurityCustomizer securityCustomizer() {
        return web -> {
            // do not handle static resources
            web.ignoring().requestMatchers("/static/**");
            // do not handle version endpoints
            web.ignoring().requestMatchers("/version/**");
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((customizer) -> customizer
                .anyRequest()
                .authenticated());
        http.oauth2Login((customizer) -> customizer
                .clientRegistrationRepository(clientRegistrationRepository())
                .successHandler(successHandler())
                .failureHandler(failureHandler()));
        http.oauth2Client(withDefaults());
        return http.build();
    }

    protected AuthenticationSuccessHandler successHandler() {
        return new EsyBackendOauth2LoginSuccessHandler();
    }

    protected AuthenticationFailureHandler failureHandler() {
        return new EsyBackendOauth2LoginFailureHandler();
    }

    protected ClientRegistrationRepository clientRegistrationRepository() {
        final var builder = ClientRegistration.withRegistrationId("github");
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        builder.clientName("Github OAuth Apps");
        builder.clientId(clientId);
        builder.clientSecret(clientSecret);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}");
        builder.authorizationUri("https://github.com/login/oauth/authorize");
        builder.tokenUri("https://github.com/login/oauth/access_token");
        builder.userInfoUri("https://api.github.com/user");
        builder.scope("read:user");
        // Define attribute readable with AuthenticatedPrincipal#getName.
        // Must not be null and should contain a valid e-mail address.
        // See also DefaultOAuth2UserService#loadUser for more details.
        builder.userNameAttributeName("email");
        return new InMemoryClientRegistrationRepository(builder.build());
    }
}
