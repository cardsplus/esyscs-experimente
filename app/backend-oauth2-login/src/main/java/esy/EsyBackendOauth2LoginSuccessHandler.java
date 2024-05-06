package esy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class EsyBackendOauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public EsyBackendOauth2LoginSuccessHandler() {
        super("/home");
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication token) throws IOException, ServletException {
        log.info(String.format("%s [%s %s, prinicipal=%s, authenticated=%b]",
                token.getClass().getSimpleName(),
                request.getMethod(),
                request.getRequestURI(),
                token.getName(),
                token.isAuthenticated()));
        super.onAuthenticationSuccess(request, response, token);
    }
}
