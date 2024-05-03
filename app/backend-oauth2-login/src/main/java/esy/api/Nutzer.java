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
public class Nutzer {
    private final UUID id;
    private final String name;
    private final String mail;

    public static List<Nutzer> loadAll() {
        final var idPrefix = "3f31e3db-1e3d-4283-a45b-";
        return List.of(
                new Nutzer(UUID.fromString(idPrefix + "000000000001"),
                        "Robert Bruckbauer", "bruckbauer@gmx.at"));
    }
}
