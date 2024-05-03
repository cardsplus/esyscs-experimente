package esy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EsyBackendOauth2LoginRunner {

    public static void main(final String[] args) {
        final var builder = new SpringApplicationBuilder(EsyBackendOauth2LoginRunner.class);
        builder.run(args);
    }
}
