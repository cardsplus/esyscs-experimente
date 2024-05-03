package esy.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Enum {
    private final UUID id;
    private final String art;
    private final Long code;
    private final String name;
    private final String text;

    public static List<Enum> loadAllKanal(final String art) {
        final var idPrefix = "1f31e3db-3e3d-4283-a45b-";
        return List.of(
                new Enum(UUID.fromString(idPrefix + "000000000001"),
                        art, 1L, "tel", "Telefon"));
    }

    public static List<Enum> loadAllLand(final String art) {
        final var idPrefix = "1f31e3db-2e3d-4283-a45b-";
        return List.of(
                new Enum(UUID.fromString(idPrefix + "000000000001"),
                        art, 1L, "AT", "Österreich"),
                new Enum(UUID.fromString(idPrefix + "000000000002"),
                        art, 2L, "DE", "Deutschland"));
    }

    public static List<Enum> loadAllQuelle(final String art) {
        final var idPrefix = "1f31e3db-3e3d-4283-a45b-";
        return List.of(
                new Enum(UUID.fromString(idPrefix + "000000000001"),
                        art, 1L, "GITHUB", "github.com"));
    }

    public static List<Enum> loadAllSprache(final String art) {
        final var idPrefix = "1f31e3db-4e3d-4283-a45b-";
        return List.of(
                new Enum(UUID.fromString(idPrefix + "000000000001"),
                        art, 1L, "DE", "Deutsch"),
                new Enum(UUID.fromString(idPrefix + "000000000002"),
                        art, 2L, "EN", "Englisch"));
    }
}
