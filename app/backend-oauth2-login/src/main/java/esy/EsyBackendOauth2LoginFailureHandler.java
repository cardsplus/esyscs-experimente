package esy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class EsyBackendOauth2LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public EsyBackendOauth2LoginFailureHandler() {
        super();
        setDefaultFailureUrl("/home");
        setAllowSessionCreation(false);
        setUseForward(false);
    }

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException cause) throws IOException, ServletException {
        log.warn(String.format("%s [%s %s]: %s",
                cause.getClass().getSimpleName(),
                request.getMethod(),
                request.getRequestURI(),
                cause.getMessage()));
        super.onAuthenticationFailure(request, response, cause);
    }
}
